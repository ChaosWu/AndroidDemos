package cn.android.demo.apis.ui.graphics;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class AndroidMatrix extends Activity {
	private ImageView myImageView;

	private Spinner spinnerScale;
	private SeekBar seekbarRotate;

	private SeekBar seekbarSkewX;
	private SeekBar seekbarSkewY;

	private TextView textSkewX;
	private TextView textSkewY;
	private TextView textRotate;

	private Button btReset;

	private ArrayAdapter<String> adapterScale;
	private Bitmap bitmap;
	private static final String[] strScale = { "0.2x", "0.5x", "1.0x", "2.0x",
			"5.0x" };
	private static final Float[] floatScale = { 0.2F, 0.5F, 1F, 2F, 5F };

	private final int defaultSpinnerScaleSelection = 2;

	private float curScale = 1F;
	private float curRotate = 0F;

	private float curSkewX = 0F;
	private float curSkewY = 0F;

	private int bmpWidth;
	private int bmpHeight;

	private boolean isClick = false;

	private String imageName = "MyHead.png";
	private String extStorageDirectory = Environment
			.getExternalStorageDirectory().toString();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_graphics_matrix);

		myImageView = (ImageView) findViewById(R.id.imageview);

		spinnerScale = (Spinner) findViewById(R.id.scale);
		seekbarRotate = (SeekBar) findViewById(R.id.rotate);

		seekbarSkewX = (SeekBar) findViewById(R.id.sb_skewx);
		seekbarSkewY = (SeekBar) findViewById(R.id.sb_skewy);

		textSkewX = (TextView) findViewById(R.id.tv_skewx);
		textSkewY = (TextView) findViewById(R.id.tv_skewy);
		textRotate = (TextView) findViewById(R.id.tv_rotate);

		btReset = (Button) findViewById(R.id.bt_matrix_reset);

		adapterScale = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, strScale);
		adapterScale
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinnerScale.setAdapter(adapterScale);
		spinnerScale.setSelection(defaultSpinnerScaleSelection);
		curScale = floatScale[defaultSpinnerScaleSelection];

		bitmap = BitmapFactory
				.decodeFile(extStorageDirectory + "/" + imageName);
		bmpWidth = bitmap.getWidth();
		bmpHeight = bitmap.getHeight();

		// drawMatrix();

		spinnerScale.setOnItemSelectedListener(itemSelectedListener);
		seekbarRotate.setOnSeekBarChangeListener(rotateBarChangeListener);

		seekbarSkewX.setOnSeekBarChangeListener(skewXBarChangeListener);
		seekbarSkewY.setOnSeekBarChangeListener(skewYBarChangeListener);

		btReset.setOnClickListener(clickListener);
	}

	private void drawMatrix() {
		Matrix matrix = new Matrix();
		// 缩放
		matrix.postScale(curScale, curScale);
		// 旋转
		matrix.postRotate(curRotate);
		// 倾斜
		matrix.postSkew(curSkewX, curSkewY);
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bmpWidth,
				bmpHeight, matrix, true);
		myImageView.setImageBitmap(resizedBitmap);

	}

	private OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			if (!isClick) {
				isClick = true;
				Matrix matrix = new Matrix();
				Matrix matrixMirrorY = new Matrix();
				float[] mirrorY = { 
						-1, 0, 0,
						0, 1, 0, 
						0, 0, 1 };
				matrixMirrorY.setValues(mirrorY);
				matrix.postConcat(matrixMirrorY);

				Bitmap mirrorBitmap = Bitmap.createBitmap(bitmap, 0, 0,
						bmpWidth, bmpHeight, matrix, true);
				myImageView.setImageBitmap(mirrorBitmap);
			} else {
				isClick = false;
				myImageView.setImageBitmap(bitmap);
			}
		}
	};

	private OnSeekBarChangeListener skewXBarChangeListener = new OnSeekBarChangeListener() {

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
			curSkewX = (float) (progress - 100) / 100;
			textSkewX.setText("Skew-X: " + String.valueOf(curSkewX));
			drawMatrix();
		}
	};

	private OnSeekBarChangeListener skewYBarChangeListener = new OnSeekBarChangeListener() {

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
			curSkewY = (float) (progress - 100) / 100;
			textSkewY.setText("Skew-Y: " + String.valueOf(curSkewY));
			drawMatrix();
		}
	};
	private OnSeekBarChangeListener rotateBarChangeListener = new OnSeekBarChangeListener() {

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
			curRotate = (float) progress;
			textRotate.setText("Rotate: " + progress);
			drawMatrix();
		}
	};
	private OnItemSelectedListener itemSelectedListener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			curScale = floatScale[position];
			drawMatrix();

		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			spinnerScale.setSelection(defaultSpinnerScaleSelection);
			curScale = floatScale[defaultSpinnerScaleSelection];
		}
	};
}
