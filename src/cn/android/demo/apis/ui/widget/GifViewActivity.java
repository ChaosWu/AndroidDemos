package cn.android.demo.apis.ui.widget;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * 用系统Movie 来显示GIF动画
 * 
 * @author Elroy
 * 
 */
public class GifViewActivity extends Activity {
	TextView tvInfo;
	GifView gifView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_widget_gif_movie);
		gifView = $id(R.id.movie_gifview);
		tvInfo = $id(R.id.movie_gif_textinfo);

		String strInfo = "";
		strInfo += "Duration:" + gifView.getMovieDuration() + "\n";
		strInfo += "W x H" + gifView.getMovieWidth() + " x "
				+ gifView.getMovieHeight() + "\n";

		tvInfo.setText(strInfo);
	}

	public <T extends View> T $id(int id) {
		return (T) findViewById(id);
	}
}
