package cn.android.demo.apis.ui.widget;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cn.android.demo.apis.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

/**
 * load movie with Movie.decodeByteArray()
 * 
 * instead of loading with Movie.decodeStream()
 * 
 * @author Elroy
 * 
 */
public class GifView extends View {

	private InputStream gifInputStream;
	private Movie gifMovie;
	private int movieWidth, movieHeight;
	private long movieDuration;
	private long mMovieStart;

	public GifView(Context context) {
		super(context);
		init(context);
	}

	public GifView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public GifView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	private void init(Context context) {
		setFocusable(true);
		gifInputStream = context.getResources().openRawResource(
				R.drawable.android_er_gif);
		// TODO 两种写法（负载与装载的两种写法）
		// 装载
		// gifMovie = Movie.decodeStream(gifInputStream);

		// 负载
		byte[] array = streamToBytes(gifInputStream);
		gifMovie = Movie.decodeByteArray(array, 0, array.length);

		movieWidth = gifMovie.width();
		movieHeight = gifMovie.height();
		movieDuration = gifMovie.duration();
	}

	private static byte[] streamToBytes(InputStream is) {
		ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
		byte[] buffer = new byte[1024];
		int len;
		try {
			while ((len = is.read(buffer)) >= 0) {
				os.write(buffer, 0, len);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return os.toByteArray();

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(movieWidth, movieHeight);
	}

	public int getMovieWidth() {
		return movieWidth;
	}

	public int getMovieHeight() {
		return movieHeight;
	}

	public long getMovieDuration() {
		return movieDuration;
	}

	@Override
	protected void onDraw(Canvas canvas) {

		long now = android.os.SystemClock.uptimeMillis();
		if (mMovieStart == 0) { // first time
			mMovieStart = now;
		}

		if (gifMovie != null) {

			int dur = gifMovie.duration();
			if (dur == 0) {
				dur = 1000;
			}

			int relTime = (int) ((now - mMovieStart) % dur);

			gifMovie.setTime(relTime);

			gifMovie.draw(canvas, 0, 0);
			invalidate();

		}

	}

}