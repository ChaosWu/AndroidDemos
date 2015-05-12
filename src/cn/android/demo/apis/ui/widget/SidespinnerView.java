package cn.android.demo.apis.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.android.demo.apis.R;

/**
 * attr 自定义属性
 * 
 * 保存和恢复状态(解决屏幕旋转的问题)
 * 
 * @author Elroy
 * 
 */
public class SidespinnerView extends LinearLayout {
	private Button btNext;
	private Button btPrev;

	private CharSequence[] mSpinnerValues = null;
	private int mSelectedIndex = -1;

	/**
	 * Identifier for the state to save the selected index of the side spinner.
	 */
	private static String STATE_SELECTED_INDEX = "SelectedIndex";

	/**
	 * Identifier for the state of the super class.
	 */
	private static String STATE_SUPER_CLASS = "SuperClass";

	public SidespinnerView(Context context) {
		this(context, null);
		init(context);
	}

	public SidespinnerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray array = context.obtainStyledAttributes(attrs,
				R.styleable.SideSpinner);
		mSpinnerValues = array.getTextArray(R.styleable.SideSpinner_values);
		array.recycle();

		init(context);
	}

	public SidespinnerView(Context context, AttributeSet attrs, int defStyle) {
		this(context, attrs);
		init(context);
	}

	private void init(Context context) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.ui_widget_compound_sidespinner_view, this);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

		btNext = (Button) this.findViewById(R.id.bt_sidespinner_view_next);
		btNext.setText("下一页");
		btPrev = (Button) this.findViewById(R.id.bt_sidespinner_view_prev);
		btPrev.setText("上一页");

		btNext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mSpinnerValues != null
						&& mSelectedIndex < mSpinnerValues.length - 1) {
					int newSelecteIndex = mSelectedIndex + 1;
					setSelectedIndex(newSelecteIndex);

				}
			}
		});

		btPrev.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mSelectedIndex > 0) {
					int newSelecteIndex = mSelectedIndex - 1;
					setSelectedIndex(newSelecteIndex);

				}
			}
		});
		// Select the first value by default.
		setSelectedIndex(0);
	}

	/**
	 * Sets the list of value in the spinner, selecting the first value by
	 * default.
	 * 
	 * @param values
	 *            the values to set in the spinner.
	 */
	public void setValues(CharSequence[] values) {
		mSpinnerValues = values;
		// Select the first item of the string array by default since
		// the list of value has changed.
		setSelectedIndex(0);
	}

	/**
	 * Sets the selected index of the spinner.
	 * 
	 * @param index
	 *            the index of the value to select.
	 */
	private void setSelectedIndex(int index) {
		// If no values are set for the spinner, do nothing.
		if (mSpinnerValues == null || mSpinnerValues.length == 0) {
			return;
		}
		// If the index value is invalid, do nothing
		if (index < 0 || index >= mSpinnerValues.length) {
			return;
		}

		// Set the current index and display the value.
		mSelectedIndex = index;
		TextView currentValue;

		currentValue = (TextView) this.findViewById(R.id.tv_sidespinner_value);
		currentValue.setText(mSpinnerValues[index]);
		// If the first value is shown, hide the previous button.
		if (mSelectedIndex == 0) {
			btPrev.setVisibility(INVISIBLE);

		} else {
			btPrev.setVisibility(VISIBLE);
		}
		// If the last value is shown, hide the next button.
		if (mSelectedIndex == mSpinnerValues.length - 1) {
			btNext.setVisibility(INVISIBLE);

		} else {
			btNext.setVisibility(VISIBLE);
		}

	}

	/**
	 * Gets the selected value of the spinner, or null if no valid selected
	 * index is set yet.
	 * 
	 * @return the selected value of the spinner.
	 */
	public CharSequence getSelecteValue() {

		// If no values are set for the spinner, return an empty string.
		if (mSpinnerValues == null || mSpinnerValues.length == 0) {
			return "";

		}
		// If the current index is invalid, return an empty string.
		if (mSelectedIndex < 0 || mSelectedIndex >= mSpinnerValues.length) {
			return "";
		}

		return mSpinnerValues[mSelectedIndex];
	}

	/**
	 * Gets the selected index of the spinner.
	 * 
	 * @return the selected index of the spinner.
	 */
	public int getSelectIndex() {
		return mSelectedIndex;
	}

	@Override
	protected Parcelable onSaveInstanceState() {
		Bundle bundle = new Bundle();
		bundle.putParcelable(STATE_SUPER_CLASS, super.onSaveInstanceState());
		bundle.putInt(STATE_SELECTED_INDEX, mSelectedIndex);
		return bundle;
	}

	@Override
	protected void onRestoreInstanceState(Parcelable state) {

		if (state instanceof Bundle) {
			Bundle bundle = (Bundle) state;
			super.onRestoreInstanceState(bundle
					.getParcelable(STATE_SUPER_CLASS));
			setSelectedIndex(bundle.getInt(STATE_SELECTED_INDEX));
		} else {
			super.onRestoreInstanceState(state);
		}

	}

	@Override
	protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
		// Makes sure that the state of the child views in the side
		// spinner are not saved since we handle the state in the
		// onSaveInstanceState.
		super.dispatchSaveInstanceState(container);
	}

	@Override
	protected void dispatchRestoreInstanceState(
			SparseArray<Parcelable> container) {
		// Makes sure that the state of the child views in the side
		// spinner are not restored since we handle the state in the
		// onSaveInstanceState.
		super.dispatchRestoreInstanceState(container);
	}

}