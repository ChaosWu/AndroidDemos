package cn.android.demo.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import android.util.Log;

public class FileUtil {
	private static final int BUFF_SIZE = 0;

	/** 
	 * 批量压缩文件（夹） 
	 * 
	 * @param resFileList 要压缩的文件（夹）列表 
	 * @param zipFile 生成的压缩文件 
	 * @throws IOException 当压缩过程出错时抛出 
	 */
	public static void zipFiles(Collection<File> resFileList, File zipFile)
			throws IOException {
		ZipOutputStream zipout = new ZipOutputStream(new BufferedOutputStream(
				new FileOutputStream(zipFile), 1024 * 1024));
		for (File resFile : resFileList) {
			zipFile(resFile, zipout, "");
		}
		zipout.close();
	}

	/** 
	    * 压缩文件 
	    * 
	    * @param resFile 需要压缩的文件（夹） 
	    * @param zipout 压缩的目的文件 
	    * @param rootpath 压缩的文件路径 
	    * @throws FileNotFoundException 找不到文件时抛出 
	    * @throws IOException 当压缩过程出错时抛出 
	    */
	private static void zipFile(File resFile, ZipOutputStream zipout,
			String rootpath) throws FileNotFoundException, IOException {
		rootpath = rootpath
				+ (rootpath.trim().length() == 0 ? "" : File.separator)
				+ resFile.getName();
		if (!resFile.exists()) {
			return;
		}
		if (resFile.isDirectory()) {
			File[] fileList = resFile.listFiles();
			for (File file : fileList) {
				zipFile(file, zipout, rootpath);
			}
			rootpath = rootpath + File.separatorChar;// 添加File.separatorChar表示该ZipEntry对应的是文件夹，也就是此时调用ZipEntry.isDirectory()会返回true
			zipout.putNextEntry(new ZipEntry(rootpath));
		} else {
			byte buffer[] = new byte[1024 * 1024];
			BufferedInputStream in = new BufferedInputStream(
					new FileInputStream(resFile), BUFF_SIZE);
			zipout.putNextEntry(new ZipEntry(rootpath));
			int realLength;
			while ((realLength = in.read(buffer)) != -1) {
				zipout.write(buffer, 0, realLength);
			}
			in.close();
			zipout.flush();
			zipout.closeEntry();
		}
	}

	/**
	 * 解压一个Zip格式压缩文件
	 * @param zipFile 需要解压缩文件
	 * @param folderPath 解压缩的目标目录
	 * @throws IOException 当解压缩过程出错时抛出
	 */
	public static void upZipFile(File zipFile, String folderPath)
			throws IOException, ZipException {
		File desDir = new File(folderPath);
		if (!desDir.exists()) {
			desDir.mkdirs();// 无论是否父目录存在，都会创建目录成功！
		}
		ZipFile zf = new ZipFile(zipFile);

		Enumeration<?> entries = zf.entries();
		while (entries.hasMoreElements()) {
			// 压缩文件里面的每一个文件或者文件夹都对应一个ZipEntry，通过zf.entries()函数可以把所有的文件或者文件夹对应的ZipEntry都读出来
			ZipEntry entry = (ZipEntry) entries.nextElement();

			String str = folderPath + File.separator + entry.getName();
			Log.i("ZIP", "file name=" + entry.getName());// 打印出压缩文件中的所有文件或者文件夹名

			File desFile = new File(str);
			if (!desFile.exists()) {
				File fileParentDir = desFile.getParentFile();
				if (!fileParentDir.exists()) {
					fileParentDir.mkdirs();
				}

				if (entry.isDirectory()) {// 用来判断当前的ZipEntry对应的文件还是文件夹
					desFile.mkdir();// mkdir()就是创建一个目录，但是前提是创建的目录的父目录一定要存在
				} else {
					desFile.createNewFile();
					InputStream is = zf.getInputStream(entry);
					OutputStream os = new FileOutputStream(desFile);
					byte buffer[] = new byte[1024 * 1024];
					int realLength;
					while ((realLength = is.read(buffer)) > 0) {
						os.write(buffer, 0, realLength);
					}
					is.close();
					os.close();
				}
			}
		}
	}
}
