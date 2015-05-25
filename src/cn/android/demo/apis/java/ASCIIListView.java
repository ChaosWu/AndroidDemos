package cn.android.demo.apis.java;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ASCIIListView extends Activity {
	ListView lv;
	AsciiCode Asciis[];
	ArrayAdapter<AsciiCode> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		lv = new ListView(this);
		setContentView(lv);
		initAsciis();

		adapter = new ArrayAdapter<AsciiCode>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, Asciis);
		lv.setAdapter(adapter);
	}

	private void initAsciis() {
		Asciis = new AsciiCode[128];
		for (int i = 0; i < 128; i++) {
			Asciis[i] = new AsciiCode(i);

		}
	}

	public class AsciiCode {
		int id;
		char charAscii;

		public AsciiCode(int id) {
			this.id = id;
			charAscii = (char) id;
		}

		@Override
		public String toString() {

			return String.format("%03d", id) + " /(hex) 0x"
					+ String.format("%02x", id).toUpperCase() + " : "
					+ charAscii;
		}
	}
}
