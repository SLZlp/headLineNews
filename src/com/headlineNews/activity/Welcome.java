package com.headlineNews.activity;

import com.headlineNews.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;

/**
 * 欢迎界面的 activity 设置了透明动画 动画执行完之后然后跳转到 mainactivity
 * 
 * @author susan
 * 
 */
public class Welcome extends Activity {
	// 定义了 一个 透明动画
	private AlphaAnimation start_anima;
	// 定义一个 视图 用来 加载布局
	private View view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		setContentView(view);

		initData();
		initListener();
	}
	
	// 动画结束的 就跳转 到mainactivity
	private void redirectTo() {
		startActivity(new Intent(getApplicationContext(), MainActivity.class));
		finish();
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		view = View.inflate(this, R.layout.welcome, null);
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		// start_anima = new AlphaAnimation(0.2f, 1.0f);
		start_anima = (AlphaAnimation) AnimationUtils.loadAnimation(this,
				R.anim.abc_fade_in);
		start_anima.setDuration(2000);
		view.startAnimation(start_anima);
	}

	/**
	 * 初始化监听
	 */
	private void initListener() {
		start_anima.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				redirectTo();
			}
		});
	}

}
