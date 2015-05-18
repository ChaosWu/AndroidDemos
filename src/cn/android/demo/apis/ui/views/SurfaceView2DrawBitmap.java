package cn.android.demo.apis.ui.views;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SurfaceView2DrawBitmap extends Activity {

	TextView textDurA, textDurB, textDurFillBack, textDurDrawBM, textDurTotal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_view_android_surfaceview_draw_bitmap);
		textDurA = $id(R.id.sv_drawbitmap_durA);
		textDurB = $id(R.id.sv_drawbitmap_durB);
		textDurFillBack = $id(R.id.sv_drawbitmap_durFillBack);
		textDurDrawBM = $id(R.id.sv_drawbitmap_durDrawBM);
		textDurTotal = $id(R.id.sv_drawbitmap_durTotal);
	}

	protected void showDur(long dA, long dB, long dFill, long dDraw, long dTotal) {
		textDurA.setText("Duration(ms) - A: " + dA);
		textDurB.setText("Duration(ms) - B: " + dB);
		textDurFillBack.setText("Duration(ms) - Fill Background: " + dFill);
		textDurDrawBM.setText("Duration(ms) - drawBitmap: " + dDraw);
		textDurTotal.setText("Duration(ms) - Total: " + dTotal);
	}

	public <T extends View> T $id(int id) {
		return (T) findViewById(id);
	}
}
