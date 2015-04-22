package cn.android.demo.apis.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Parcelable demo
 * 
 * Parcelable 和Serializable 区别：
 * 
 * 1、 P比S性能高 2、S在序列化的时候会产生大量临时变量，从而引起频繁GC
 * 3、不能使用在要将数据存储在磁盘上的情况，因为Parcelable不能很好的保证数据的持续性在外界有变化的情况下 。尽管S效率低点，但此时还是建议使用S
 * 
 * 
 * 
 * 
 * 
 * ******************************
 * 
 * 1、永久性保存对象，保存对象的字节序列到本地文件中
 * 
 * 2、通过序列化对象在网络中传递对象
 * 
 * 3、通过序列化在进程间传递对象
 * 
 * @author Elroy
 * 
 */
public class MyParcelable implements Parcelable {
	public String name;
	public String address;

	// 读取接口，目的是要从Parcel中构造一个实现了Parcelable的类的实例处理。因为实现类在这里还是不可知的，所以需要用到模板的方式，继承类名通过模板参数传入
	// 为了能够实现模板参数的传入，这里定义Creator嵌入接口,内含两个接口函数分别返回单个和多个继承类实例
	public static final Parcelable.Creator<MyParcelable> CREATOR = new Creator<MyParcelable>() {

		@Override
		public MyParcelable[] newArray(int size) {
			return new MyParcelable[size];
		}

		// Parcel对象映射成你的对象
		@Override
		public MyParcelable createFromParcel(Parcel source) {
			Log.v("EEE", "createFromParcel^");
			return new MyParcelable(source);
		}
	};

	public MyParcelable() {
	}

	public MyParcelable(Parcel source) {
		readFromParcel(source);
	}

	private void readFromParcel(Parcel source) {
		Log.v("EEE", "readFromParcel^");
		name = source.readString();
		address = source.readString();
	}

	// 内容描述接口，基本不用管
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	// 写入接口函数，打包、映射成Parcel对象
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		Log.v("EEE", "writeToParcel^");
		dest.writeString(name);
		dest.writeString(address);
	}

}
