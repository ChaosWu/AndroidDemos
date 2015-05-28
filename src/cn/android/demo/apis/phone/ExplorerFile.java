package cn.android.demo.apis.phone;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import cn.android.demo.apis.R;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.media.ExifInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ExplorerFile extends ListActivity {
	private List<String> item = null;
	private List<String> path = null;

	private TextView mPath;

	private String root = "/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_explorer_file);
		mPath = (TextView) findViewById(R.id.tv_file_path);

		getListView().setTextFilterEnabled(true);
		getDir(root);

	}

	private void getDir(String dirPath) {
		mPath.setText("Location: " + dirPath);

		item = new ArrayList<String>();
		path = new ArrayList<String>();

		File file = new File(dirPath);
		File[] files = file.listFiles();

		if (!dirPath.equals(root)) {
			item.add(root);
			path.add(root);

			item.add("../");
			path.add(file.getParent());

		}
		// 按照文件名排序
		// Arrays.sort(files, filecomparator);

		// 按照时间排序
		Arrays.sort(files, filecomparatorByLastModified);
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			path.add(f.getPath());

			if (f.isDirectory()) {
				item.add(f.getName() + "/");

			} else {
				item.add(f.getName());
			}

			ArrayAdapter<String> fileList = new ArrayAdapter<String>(this,
					R.layout.listview_textview_item, item);
			setListAdapter(fileList);
		}

	}

	// 排序方法
	Comparator<? super File> filecomparator = new Comparator<File>() {

		@Override
		public int compare(File lhs, File rhs) {

			if (lhs.isDirectory()) {
				if (rhs.isDirectory()) {
					return String.valueOf(lhs.getName().toLowerCase())
							.compareTo(rhs.getName().toLowerCase());

				} else {
					return -1;
				}
			} else {
				if (rhs.isDirectory()) {
					return 1;

				} else {
					return String.valueOf(lhs.getName().toLowerCase())
							.compareTo(rhs.getName().toLowerCase());
				}

			}

		}

	};
	Comparator<? super File> filecomparatorByLastModified = new Comparator<File>() {

		public int compare(File file1, File file2) {

			if (file1.isDirectory()) {
				if (file2.isDirectory()) {
					return Long.valueOf(file1.lastModified()).compareTo(
							file2.lastModified());
				} else {
					return -1;
				}
			} else {
				if (file2.isDirectory()) {
					return 1;
				} else {
					return Long.valueOf(file1.lastModified()).compareTo(
							file2.lastModified());
				}
			}

		}
	};

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		File file = new File(path.get(position));
		if (file.isDirectory()) {
			if (file.canRead()) {
				getDir(path.get(position));
			} else {

				Toast.makeText(this, "文件不能读取！", 1).show();

			}
		} else {
			String exifAttribute = null;
			String filename = file.getName();
			String ext = filename.substring(filename.lastIndexOf('.') + 1,
					filename.length());

			if (ext.equals("JPG") || ext.equals("jpg")) {
				try {
					ExifInterface exif = new ExifInterface(file.toString());
					exifAttribute = getExif(exif);
				} catch (IOException e) {
					// TODO Auto-generated catch block
				}
			}
			new AlertDialog.Builder(this)
					.setIcon(R.drawable.ic_launcher)
					.setTitle("[" + filename + "]")
					.setMessage(exifAttribute)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
								}
							}).show();

		}

	}

	private String getExif(ExifInterface exif) {
		String myAttribute = null;
		myAttribute += getTagString(ExifInterface.TAG_DATETIME, exif);
		myAttribute += getTagString(ExifInterface.TAG_FLASH, exif);
		myAttribute += getTagString(ExifInterface.TAG_GPS_LATITUDE, exif);
		myAttribute += getTagString(ExifInterface.TAG_GPS_LATITUDE_REF, exif);
		myAttribute += getTagString(ExifInterface.TAG_GPS_LONGITUDE, exif);
		myAttribute += getTagString(ExifInterface.TAG_GPS_LONGITUDE_REF, exif);
		myAttribute += getTagString(ExifInterface.TAG_IMAGE_LENGTH, exif);
		myAttribute += getTagString(ExifInterface.TAG_IMAGE_WIDTH, exif);
		myAttribute += getTagString(ExifInterface.TAG_MAKE, exif);
		myAttribute += getTagString(ExifInterface.TAG_MODEL, exif);
		myAttribute += getTagString(ExifInterface.TAG_ORIENTATION, exif);
		myAttribute += getTagString(ExifInterface.TAG_WHITE_BALANCE, exif);
		return myAttribute;
	}

	private String getTagString(String tag, ExifInterface exif) {
		return (tag + " : " + exif.getAttribute(tag) + "\n");
	}
}
