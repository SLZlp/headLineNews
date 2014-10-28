package com.headlineNews.activity;

import com.headlineNews.R;
import com.headlineNews.baseActivity.BaseActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 设置界面的Activity
 * @author Administrator
 *
 * @author zhu
 */
public class SettingsActivity extends BaseActivity implements OnClickListener {

	// 这是初始化title_bar布局的两个控件
	TextView title;
	TextView right_text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);

		initView();
		initData();

	}

	private void initView() {
		title = (TextView) findViewById(R.id.title);
		right_text = (TextView) findViewById(R.id.right_title);
		right_text.setVisibility(View.VISIBLE);
		right_text.setClickable(true);
		right_text.setOnClickListener(this);
	}

	private void initData() {
		title.setText("设置");
		right_text.setText("意见反馈");
	}

	/**
	 * 按返回键的时候 调用动画 从左边，到右边 这里应该要判断下，如果页面打开时动画师从左到右的， 那么按返回键应该是与之相反的动画效果
	 */
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		// overridePendingTransition(R.anim.slide_in_right,
		// R.anim.slide_out_left);
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

	/**
	 * 判断是哪个id的点击事件，然后根据不同点击事件，去做不同的事
	 * 
	 * @param v
	 */

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.right_title:

			break;
		default:
			break;
		}
	}

}
