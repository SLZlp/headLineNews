package com.headlineNews.baseActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * 基类的 activity
 * 
 * @author susan
 * 
 */
public class BaseActivity extends Activity {

	/** 手势监听 */
	GestureDetector mGestureDetector;

	/** 是否需要监听手势关闭功能 */
	private boolean mNeedBackGesture = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initView();
		initData();
		initListener();
	}
	/**
	 * 初始化控件
	 */
	private void initView() {

	}
	/**
	 * 初始化数据
	 */
	private void initData() {

	}
	/**
	 * 初始化监听
	 */
	private void initListener() {

		// 初始化手势监听
		if (mGestureDetector == null) {
			mGestureDetector = new GestureDetector(getApplicationContext(),
					new BackGestureListener(this));
		}
	}

	/**
	 * 调度触摸事件
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (mNeedBackGesture) {
			return mGestureDetector.onTouchEvent(ev)
					|| super.dispatchTouchEvent(ev);
		}
		return super.dispatchTouchEvent(ev);
	}

	/**
	 * 设置是否进行手势监听
	 * @param mNeedBackGesture 是否手势关闭
	 */
	public void setNeedBackGesture(boolean mNeedBackGesture) {
		this.mNeedBackGesture = mNeedBackGesture;
	}

	/**
	 *  封装事件返回
	 * @param view
	 */
	public void doBack(View view) {
		onBackPressed();
	}

}
