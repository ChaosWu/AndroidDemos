package cn.android.demo.apis.phone;

import java.io.FileNotFoundException;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class IntentActionOpenDocument extends Activity {

	Button button;
	TextView textView;
	ImageView imageView;
	Uri uri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_android_intent_action_open_document);
		button = $id(R.id.intent_action_opendocument);
		textView = $id(R.id.intent_action_texturi);
		imageView = $id(R.id.intent_action_image);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
					intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
				} else {
					intent.setAction(Intent.ACTION_GET_CONTENT);
				}

				intent.addCategory(Intent.CATEGORY_OPENABLE);

				intent.setType("image/*");
				startActivityForResult(intent, 1);
			}
		});
		textView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (uri != null) {
					Bitmap bm;

					try {
						bm = BitmapFactory.decodeStream(getContentResolver()
								.openInputStream(uri));
						imageView.setImageBitmap(bm);

					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			Uri dataUri = data.getData();
			if (requestCode == 1) {
				uri = dataUri;
				textView.setText(uri.toString());

			}

		}

	}

	public <T extends View> T $id(int id) {
		return (T) findViewById(id);
	}
}
