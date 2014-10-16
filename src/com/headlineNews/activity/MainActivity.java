package com.headlineNews.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;

import com.headlineNews.R;
import com.headlineNews.slidingmenu.lib.SlidingMenu;
import com.headlineNews.widget.DrawerView;

/**
 * 
 * @author jack
 *
 */
public class MainActivity extends Activity {

	/**屏幕宽度*/
	private int mScreenWidth;
	/**slidingMenu实例*/
	private SlidingMenu side_drawer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		screenWidth();
		initView();
		initSlidingMenu();
	}

	/**
	 * 初始化SlidingMenu
	 */
	private void initSlidingMenu() {
		side_drawer = new DrawerView(this).initSlidingMenu();
	}

	/**
	 * 初始化视图
	 */
	private void initView() {
		
	}

	/**
	 * 得到屏幕宽度
	 */
	private void screenWidth() {
		Display display = getWindowManager().getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		display.getMetrics(metrics);
		mScreenWidth = metrics.widthPixels;
	}

}
