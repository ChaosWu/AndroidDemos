package cn.android.demo.apis.other.keyboard;

import cn.android.demo.apis.R;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.media.AudioManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;

/**
 * 自定义软键盘 服务
 * 
 * 最主要的是通过View的 InputConnection
 * 
 * @author Elroy
 * 
 */
public class SimpleIME extends InputMethodService implements
		OnKeyboardActionListener {

	private KeyboardView kv;
	private Keyboard keyboard;
	private boolean caps = false;

	@Override
	public void onPress(int primaryCode) {
	}

	@Override
	public void onRelease(int primaryCode) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onKey(int primaryCode, int[] keyCodes) {
		InputConnection ic = getCurrentInputConnection();
		playClick(primaryCode);

		switch (primaryCode) {
		case Keyboard.KEYCODE_DELETE:
			ic.deleteSurroundingText(1, 0);
			break;

		case Keyboard.KEYCODE_SHIFT:
			caps = !caps;
			keyboard.setShifted(caps);
			kv.invalidateAllKeys();
			break;
		case Keyboard.KEYCODE_DONE:
			ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN,
					KeyEvent.KEYCODE_ENTER));
			break;
		default:
			//
			char code = (char) primaryCode;
			if (Character.isLetter(code) && caps) {
				code = Character.toUpperCase(code);
			}
			ic.commitText(String.valueOf(code), 1);
		}
	}

	private void playClick(int primaryCode) {
		AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
		switch (primaryCode) {

		case 55:
			am.playSoundEffect(AudioManager.FX_FOCUS_NAVIGATION_DOWN);
			break;
		case 56:
			am.playSoundEffect(AudioManager.FX_FOCUS_NAVIGATION_LEFT);
			break;
		case 57:
			am.playSoundEffect(AudioManager.FX_FOCUS_NAVIGATION_RIGHT);
			break;
		case 48:
			am.playSoundEffect(AudioManager.FX_FOCUS_NAVIGATION_UP);
			break;
		default:
			am.playSoundEffect(AudioManager.VIBRATE_TYPE_RINGER);
		}
	}

	@Override
	public void onText(CharSequence text) {
		// TODO Auto-generated method stub

	}

	@Override
	public void swipeLeft() {
		// TODO Auto-generated method stub

	}

	@Override
	public void swipeRight() {
		// TODO Auto-generated method stub

	}

	@Override
	public void swipeDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void swipeUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public View onCreateInputView() {

		kv = (KeyboardView) getLayoutInflater().inflate(
				R.layout.other_keyboard_kv, null);
		keyboard = new Keyboard(this, R.xml.qwerty);
		kv.setKeyboard(keyboard);
		kv.setOnKeyboardActionListener(this);

		return kv;
	}

}
