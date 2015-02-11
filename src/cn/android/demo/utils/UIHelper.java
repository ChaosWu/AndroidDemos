package cn.android.demo.utils;

import cn.android.demo.AppContext;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;

/**
 * UI帮助类 Created by Elroy on 2014/11/7.
 */
public class UIHelper {

	/** 获取上下文 */
	public static Context getContext() {
		return AppContext.getApplication();
	}

	/** 获取资源 */
	public static Resources getResources() {
		return getContext().getResources();
	}

	/** 获取图片 */
	public static Bitmap getBitmap(int resId) {
		return BitmapFactory.decodeResource(getResources(), resId);
	}

	/** 获取文字 */
	public static String getString(int resId) {
		return getResources().getString(resId);
	}

	/** 获取文字数组 */
	public static String[] getStringArray(int resId) {
		return getResources().getStringArray(resId);
	}

	/** 获取dimen */
	public static int getDimens(int resId) {
		return getResources().getDimensionPixelSize(resId);
	}

	/** 获取drawable */
	public static Drawable getDrawable(int resId) {
		return getResources().getDrawable(resId);
	}

	/** 获取颜色 */
	public static int getColor(int resId) {
		return getResources().getColor(resId);
	}

	/** 颜色 将字符串转成int */
	public static int parseColor(String colorString) {
		return Color.parseColor(colorString);
	}

	/** 获取颜色选择器 */
	public static ColorStateList getColorStateList(int resId) {
		return getResources().getColorStateList(resId);
	}

	/** 获取屏幕 */
	public static DisplayMetrics getDisplayMetrics() {
		return getResources().getDisplayMetrics();
	}

	/** View 转 图片 */
	public Bitmap createViewBitmap(View v) {
		Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(),
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		v.draw(canvas);
		return bitmap;
	}

	/** dip转换px */
	public static int dip2px(int dip) {

		final float scale = getContext().getResources().getDisplayMetrics().density;
		return (int) (dip * scale + 0.5f);
	}

	/** pxz转换dip */
	public static int px2dip(int px) {
		final float scale = getContext().getResources().getDisplayMetrics().density;
		return (int) (px / scale + 0.5f);
	}

	public static View inflate(int resId) {
		return LayoutInflater.from(getContext()).inflate(resId, null);
	}
}
