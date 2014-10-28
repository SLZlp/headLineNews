package com.headlineNews.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.headlineNews.R;
import com.headlineNews.baseActivity.BaseActivity;

/** 搜索页面 */
public class SearchActivity extends BaseActivity implements OnClickListener {

	private ImageView iv_search;
	private ImageView iv_delete;
	private TextView tv_cancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		initView();
		initListener();
	}

	private void initView() {
		iv_search = (ImageView) findViewById(R.id.iv_search);
		iv_delete = (ImageView) findViewById(R.id.iv_delete);
		tv_cancel = (TextView) findViewById(R.id.tv_cancel);

	}

	private void initListener() {
		iv_search.setOnClickListener(this);
		iv_delete.setOnClickListener(this);
		tv_cancel.setOnClickListener(this);

	}

	/**
	 * 按返回键的时候 调用动画 从左边，到右边 这里应该要判断下，如果页面打开时动画师从左到右的， 那么按返回键应该是与之相反的动画效果
	 */
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_search:

			break;
		case R.id.iv_delete:

			break;

		case R.id.tv_cancel:

			break;

		default:
			break;
		}

	}

}
