package com.headlineNews.activity;


import com.headlineNews.R;
import com.headlineNews.baseActivity.BaseActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * 收藏页面
 * @author Administrator
 *
 */
public class CollectionActivity extends BaseActivity implements OnClickListener{
	
	
	private ImageView iv_Return;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.collection);
		
		initView();
		initListener();
		
	}

	private void initView() {
		iv_Return=(ImageView) findViewById(R.id.iv_Return);
		
	}
	
	private void initListener() {
		iv_Return.setOnClickListener(this);
		
	}
	
	/**
	 * 按返回键的时候 调用动画 从左边，到右边 这里应该要判断下，
	 * 如果页面打开时动画师从左到右的， 那么按返回键应该是与之相反的动画效果
	 */
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_Return:
			
			break;

		default:
			break;
		}
		
	}

	
}
