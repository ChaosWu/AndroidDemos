package cn.android.demo.apis.ui.widget;

import cn.android.demo.apis.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * LAYER_TYPE_NONE:view按一般方式绘制，不使用离屏缓冲．这是默认的行为．
 * 
 * LAYER_TYPE_HARDWARE:如果应用被硬加速了,view会被绘制到一个硬件纹理中．如果应用没被硬加速，
 * 此类型的layer的行为同于LAYER_TYPE_SOFTWARE． •
 * 
 * LAYER_TYPE_SOFTWARE:view被绘制到一个bitmap中．
 * 
 * @author Elroy
 * 
 */
@SuppressLint("NewApi")
public class DrawPathCustomView extends Activity {

	ShapeRadiusRatioView srrv;
	SeekBar sb;// radius
	SeekBar sb1;// numb
	SeekBar sb2;// innerRadius
	SeekBar sb3;// rotate

	TextView ptTV;
	TextView tvLayerInfo;
	TextView tvRotateInfo;
	final static int MIN_PT = 3;

	RadioButton optLayerTypeNone, optLayerTypeSoftware, optLayerTypeHardware;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_widget_draw_path_custom_view);
		srrv = (ShapeRadiusRatioView) findViewById(R.id.srrv);
		sb = (SeekBar) findViewById(R.id.srrv_radiusbar);
		sb1 = (SeekBar) findViewById(R.id.srrv_ptbar);
		sb2 = (SeekBar) findViewById(R.id.srrv_innerradiusbar);
		sb3 = (SeekBar) findViewById(R.id.srrv_rotatebar);

		optLayerTypeNone = (RadioButton) findViewById(R.id.srrv_typeNone);
		optLayerTypeSoftware = (RadioButton) findViewById(R.id.srrv_typeSoftware);
		optLayerTypeHardware = (RadioButton) findViewById(R.id.srrv_typeHardware);

		tvLayerInfo = (TextView) findViewById(R.id.srrv_typeinfo);
		tvRotateInfo = (TextView) findViewById(R.id.srrv_rottext);
		ptTV = (TextView) findViewById(R.id.srrv_pttext);

		srrv.passElements(tvLayerInfo);
		srrv.setLayerType(View.LAYER_TYPE_NONE, null);

		optLayerTypeNone
				.setOnCheckedChangeListener(optLayerTypeOnCheckedChangeListener);
		optLayerTypeSoftware
				.setOnCheckedChangeListener(optLayerTypeOnCheckedChangeListener);
		optLayerTypeHardware
				.setOnCheckedChangeListener(optLayerTypeOnCheckedChangeListener);

		float defaultRatio = (float) sb.getProgress() / (float) sb.getMax();
		float defaultInnerRatio = (float) (sb2.getProgress())
				/ (float) (sb2.getMax());

		srrv.setShapeRadiusRatio(defaultRatio);

		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				float defaultRatio = (float) sb.getProgress()
						/ (float) sb.getMax();
				srrv.setShapeRadiusRatio(defaultRatio);
				srrv.invalidate();
			}
		});
		sb1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				int pt = progress + MIN_PT;
				ptTV.setText("number of point in polygon: "
						+ String.valueOf(pt));
				srrv.setNumberOfPoint(pt);
				srrv.invalidate();
			}
		});

		sb2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				float ratio = (float) (sb2.getProgress())
						/ (float) (sb2.getMax());
				srrv.setShapeInnerRadiusRatio(ratio);
				srrv.invalidate();
			}
		});
		sb3.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				int degree = progress - 180;
				tvRotateInfo.setText("rotate:" + degree + "  degree");
				srrv.setShapeRotate(degree);
				srrv.invalidate();

			}
		});

	}

	OnCheckedChangeListener optLayerTypeOnCheckedChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if (optLayerTypeNone.isChecked()) {
				srrv.setLayerType(View.LAYER_TYPE_NONE, null);

			} else if (optLayerTypeSoftware.isChecked()) {
				srrv.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

			} else if (optLayerTypeHardware.isChecked()) {
				srrv.setLayerType(View.LAYER_TYPE_HARDWARE, null);

			}
			srrv.invalidate();
		}
	};
}
