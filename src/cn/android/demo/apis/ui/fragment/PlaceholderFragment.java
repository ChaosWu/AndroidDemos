package cn.android.demo.apis.ui.fragment;

import cn.android.demo.apis.R;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PlaceholderFragment extends Fragment {
	private int stateMediaPlayer;

	private final int STATE_IDLE = 0;
	private final int STATE_INITIALIZED = 1;
	private final int STATE_PREPARING = 2;
	private final int STATE_PREPARED = 3;
	private final int STATE_STARTED = 4;
	private final int STATE_PAUSED = 5;
	private final int STATE_STOPPED = 6;
	private final int STATE_PLAYBACKCOMPLETED = 7;
	private final int STATE_END = 8;
	private final int STATE_ERROR = 9;

	Button btnStart, btnPause, btnStop, btnSeek;
	TextView textState, textDuration, textPosition;

	private MediaPlayer mediaPlayer;

	public PlaceholderFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initMediaPlayer();
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.ui_fragment_placeholder,
				container, false);

		textState = (TextView) rootView
				.findViewById(R.id.ui_fragment_placeholder_state);
		textDuration = (TextView) rootView
				.findViewById(R.id.ui_fragment_placeholder_duration);
		textPosition = (TextView) rootView
				.findViewById(R.id.ui_fragment_placeholder_position);

		btnStart = (Button) rootView
				.findViewById(R.id.ui_fragment_placeholder_start);
		btnPause = (Button) rootView
				.findViewById(R.id.ui_fragment_placeholder_pause);
		btnStop = (Button) rootView
				.findViewById(R.id.ui_fragment_placeholder_stop);
		btnSeek = (Button) rootView
				.findViewById(R.id.ui_fragment_placeholder_seekto);

		btnStart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (stateMediaPlayer == STATE_PREPARED
						|| stateMediaPlayer == STATE_STARTED
						|| stateMediaPlayer == STATE_PAUSED
						|| stateMediaPlayer == STATE_PLAYBACKCOMPLETED) {
					mediaPlayer.start();
					setPlayerState(STATE_STARTED);
					displayDurationPosition();
				} else {
					Toast.makeText(getActivity(), "Play at Invalid state!",
							Toast.LENGTH_LONG).show();
				}
			}
		});

		btnPause.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (stateMediaPlayer == STATE_STARTED
						|| stateMediaPlayer == STATE_PAUSED
						|| stateMediaPlayer == STATE_PLAYBACKCOMPLETED) {
					mediaPlayer.pause();
					setPlayerState(STATE_PAUSED);
					displayDurationPosition();
				} else {
					Toast.makeText(getActivity(), "Pause at Invalid state!",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		btnStop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (stateMediaPlayer == STATE_PREPARED
						|| stateMediaPlayer == STATE_STARTED
						|| stateMediaPlayer == STATE_STOPPED
						|| stateMediaPlayer == STATE_PAUSED
						|| stateMediaPlayer == STATE_PLAYBACKCOMPLETED) {

					// Stop
					mediaPlayer.stop();
					setPlayerState(STATE_STOPPED);

					// then parepare in background thread
					mediaPlayer.prepareAsync();
					setPlayerState(STATE_PREPARING);

					displayDurationPosition();
				} else {
					Toast.makeText(getActivity(), "Stop at Invalid state!",
							Toast.LENGTH_LONG).show();
				}

			}
		});
		btnSeek.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (stateMediaPlayer == STATE_PREPARED
						|| stateMediaPlayer == STATE_STARTED
						|| stateMediaPlayer == STATE_PAUSED
						|| stateMediaPlayer == STATE_PLAYBACKCOMPLETED) {
					mediaPlayer.seekTo(0);

					displayDurationPosition();
				} else {
					Toast.makeText(getActivity(), "SeekTo at Invalid state!",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		mediaPlayer.setOnPreparedListener(new OnPreparedListener() {

			@Override
			public void onPrepared(MediaPlayer mp) {
				setPlayerState(STATE_PREPARED);
				displayDurationPosition();
			}
		});

		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				setPlayerState(STATE_PLAYBACKCOMPLETED);
				displayDurationPosition();
			}
		});

		textState.setText(getPlayerState());

		displayDurationPosition();

		textDuration.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				displayDurationPosition();
			}
		});

		textPosition.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				displayDurationPosition();
			}
		});

		return rootView;
	}

	@Override
	public void onDestroy() {

		Toast.makeText(getActivity(), "Release MediaPlayer", Toast.LENGTH_SHORT)
				.show();

		mediaPlayer.release();
		mediaPlayer = null;
		setPlayerState(STATE_END);
		super.onDestroy();

	}

	protected void displayDurationPosition() {
		textDuration.setText("Duration: " + mediaPlayer.getDuration() + " ms");
		textPosition.setText("Current Position: "
				+ mediaPlayer.getCurrentPosition() + " ms");
	}

	private void setPlayerState(int st) {
		stateMediaPlayer = st;

		String stringState = getPlayerState();
		if (textState != null) {
			textState.setText(stringState);
		} else {
			Toast.makeText(getActivity(), stringState, Toast.LENGTH_LONG)
					.show();
		}
	}

	private void initMediaPlayer() {
		Toast.makeText(getActivity(), "initMediaPlayer()", Toast.LENGTH_LONG)
				.show();
		mediaPlayer = MediaPlayer.create(getActivity(), R.raw.fssx);
		setPlayerState(STATE_PREPARED);
	}

	private String getPlayerState() {
		String strSt;
		switch (stateMediaPlayer) {
		case STATE_IDLE:
			strSt = "Idle";
			break;
		case STATE_INITIALIZED:
			strSt = "Initialized";
			break;
		case STATE_PREPARING:
			strSt = "Preparing";
			break;
		case STATE_PREPARED:
			strSt = "Prepared";
			break;
		case STATE_STARTED:
			strSt = "Started";
			break;
		case STATE_PAUSED:
			strSt = "Paused";
			break;
		case STATE_STOPPED:
			strSt = "Stopped";
			break;
		case STATE_PLAYBACKCOMPLETED:
			strSt = "PlaybackCompleted";
			break;
		case STATE_END:
			strSt = "End";
			break;
		case STATE_ERROR:
			strSt = "Error";
			break;
		default:
			strSt = "unknown...";
		}
		return strSt;
	}
}
