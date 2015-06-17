package cn.android.demo.apis.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ListView;

public class PinnedHeaderListView extends ListView {
	private static final int MAX_ALPHA = 255;

	private static final int MAX_Y_OVERSCROLL_DISTANCE = 200;// Y轴最大距离
	private int mMaxYOverscrollDistance;

	private View mHeaderView;
	private int mHeaderViewWidth;
	private int mHeaderViewHeight;
	private boolean mHeaderViewVisible;
	private PinnedHeaderAdapter mAdapter;

	public PinnedHeaderListView(Context context) {
		super(context);
		init(context);
	}

	public PinnedHeaderListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public PinnedHeaderListView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context) {
		final DisplayMetrics metrics = context.getResources()
				.getDisplayMetrics();

		final float density = metrics.density;

		mMaxYOverscrollDistance = (int) (density * MAX_Y_OVERSCROLL_DISTANCE);
	}

	@SuppressLint("NewApi")
	@Override
	protected boolean overScrollBy(int deltaX, int deltaY, int scrollX,
			int scrollY, int scrollRangeX, int scrollRangeY,
			int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
		if (!isTouchEvent) {// 禁止惯性滑动
			if ((scrollX < 0 && scrollY < 0)
					|| (scrollY > getHeight() && deltaX > 0)) {
				deltaY = 0;
			}
		}
		return super.overScrollBy(deltaX, (deltaY + 1) / 2, scrollX, scrollY,
				scrollRangeX, scrollRangeY, maxOverScrollX, mMaxYOverscrollDistance,
				isTouchEvent);
	}

	/**
	 * 绘制Header View
	 */
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		if (mHeaderViewVisible) {
			drawChild(canvas, mHeaderView, getDrawingTime());
		}
	}

	/**
	 * 核心就是根据不同的状态值来控制Header
	 * View的状态，比如PINNED_HEADER_GONE（隐藏）的情况，可能需要设置一个flag标记，不绘制Header
	 * View，那么就达到隐藏的效果。当PINNED_HEADER_PUSHED_UP状态时，可能需要根据不同的位移来计算Header
	 * View的移动位移。下面是具体的实现：
	 * 
	 * @param position
	 */
	public void configureHeaderView(int position) {
		if (mHeaderView == null || null == mAdapter) {
			return;
		}

		int state = mAdapter.getPinnedHeaderState(position);
		switch (state) {
		case PinnedHeaderAdapter.PINNED_HEADER_GONE: {
			mHeaderViewVisible = false;
			break;
		}

		case PinnedHeaderAdapter.PINNED_HEADER_VISIBLE: {
			mAdapter.configurePinnedHeader(mHeaderView, position, MAX_ALPHA);
			if (mHeaderView.getTop() != 0) {
				mHeaderView.layout(0, 0, mHeaderViewWidth, mHeaderViewHeight);
			}
			mHeaderViewVisible = true;
			break;
		}

		case PinnedHeaderAdapter.PINNED_HEADER_PUSHED_UP: {
			View firstView = getChildAt(0);
			int bottom = firstView.getBottom();
			int itemHeight = firstView.getHeight();
			int headerHeight = mHeaderView.getHeight();
			int y;
			int alpha;
			if (bottom < headerHeight) {
				y = (bottom - headerHeight);
				alpha = MAX_ALPHA * (headerHeight + y) / headerHeight;
			} else {
				y = 0;
				alpha = MAX_ALPHA;
			}
			mAdapter.configurePinnedHeader(mHeaderView, position, alpha);
			if (mHeaderView.getTop() != y) {
				mHeaderView.layout(0, y, mHeaderViewWidth, mHeaderViewHeight
						+ y);
			}
			mHeaderViewVisible = true;
			break;
		}
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if (mHeaderView != null) {
			measureChild(mHeaderView, widthMeasureSpec, heightMeasureSpec);
			mHeaderViewWidth = mHeaderView.getMeasuredWidth();
			mHeaderViewHeight = mHeaderView.getMeasuredHeight();
		}
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		if (mHeaderView != null) {
			mHeaderView.layout(0, 0, mHeaderViewWidth, mHeaderViewHeight);
			configureHeaderView(getFirstVisiblePosition());
		}
	}

	public void setPinnedHeaderView(View headerView) {
		this.mHeaderView = headerView;
	}

}
