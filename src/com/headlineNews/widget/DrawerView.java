package com.headlineNews.widget;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.headlineNews.R;
import com.headlineNews.activity.CampaignActivity;
import com.headlineNews.activity.CollectionActivity;
import com.headlineNews.activity.NoticeActivity;
import com.headlineNews.activity.SearchActivity;
import com.headlineNews.activity.SettingsActivity;
import com.headlineNews.slidingmenu.lib.SlidingMenu;
import com.headlineNews.slidingmenu.lib.SlidingMenu.OnClosedListener;

/**
 * 自定义SlidingMenu 测拉菜单类
 * 
 * @author jake
 * 
 */
public class DrawerView implements OnClickListener {

	/** Activity */
	private Activity activity;
	/** 滑动菜单 */
	private SlidingMenu localSlidingMenu;

	/** 登录今日头条 */
	private ImageView cellphone_Login_btn;
	/** 腾讯QQ登录 */
	private ImageView qqzone_btn;
	/** 微博登录 */
	private ImageView sina_weibo_btn;
	/** 更多登录方式 */
	private TextView tv_More_login;

	/** 搜索 */
	private RelativeLayout search_btn;
	/** 收藏 */
	private RelativeLayout favorite_btn;
	/** 通知 */
	private RelativeLayout message_btn;

	/** 活动 */
	private RelativeLayout app_activity_btn;
	/** 设置 */
	private RelativeLayout setting_btn;
	/** 反馈 */
	private RelativeLayout feedback_btn;

	/** 应用推荐 */
	private RelativeLayout appstore_btn;
	/** 今日特价 */
	private RelativeLayout special_offer_btn;
	/** 今日头彩 */
	private RelativeLayout the_first_prize_btn;

	public DrawerView(Activity activity) {
		super();
		this.activity = activity;
	}

	/**
	 * 
	 * 初始化滑动菜单 initSlidingMenu() localSlidingMenu
	 * 
	 * @return
	 */
	public SlidingMenu initSlidingMenu() {
		localSlidingMenu = new SlidingMenu(activity);
		localSlidingMenu.setMode(SlidingMenu.LEFT);// 设置左滑菜单
		localSlidingMenu.setTouchModeAbove(SlidingMenu.SLIDING_WINDOW);// 设置要使菜单滑动，触碰屏幕的范围
		// localSlidingMenu.setTouchModeBehind(SlidingMenu.SLIDING_CONTENT);//设置了这个会获取不到菜单里面的焦点，所以先注释掉
		localSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);// 设置阴影图片的宽度
		localSlidingMenu.setShadowDrawable(R.drawable.shadow);// 设置阴影图片
		localSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);// SlidingMenu划出时主页面显示的剩余宽度
		localSlidingMenu.setFadeDegree(0.35F);// SlidingMenu滑动时的渐变程度
		localSlidingMenu.attachToActivity(activity, SlidingMenu.LEFT);// 使SlidingMenu附加在Activity左边
		// localSlidingMenu.setBehindWidthRes(R.dimen.left_drawer_avatar_size);//设置SlidingMenu菜单的宽度
		localSlidingMenu.setMenu(R.layout.left_drawer_fragment);// 设置menu的布局文件
		// localSlidingMenu.toggle();// 动态判断自动关闭或开启SlidingMenu
		localSlidingMenu
				.setOnOpenedListener(new SlidingMenu.OnOpenedListener() {
					public void onOpened() {

					}
				});
		localSlidingMenu.setOnClosedListener(new OnClosedListener() {

			@Override
			public void onClosed() {

			}
		});

		initView();
		initListener();
		return localSlidingMenu;
	}

	/**
	 * 初始化视图
	 */
	private void initView() {
		cellphone_Login_btn = (ImageView) localSlidingMenu
				.findViewById(R.id.cellphone_Login_btn);
		qqzone_btn = (ImageView) localSlidingMenu.findViewById(R.id.qqzone_btn);
		sina_weibo_btn = (ImageView) localSlidingMenu
				.findViewById(R.id.sina_weibo_btn);
		tv_More_login = (TextView) localSlidingMenu
				.findViewById(R.id.tv_More_login);

		search_btn = (RelativeLayout) localSlidingMenu
				.findViewById(R.id.search_btn);
		favorite_btn = (RelativeLayout) localSlidingMenu
				.findViewById(R.id.favorite_btn);
		message_btn = (RelativeLayout) localSlidingMenu
				.findViewById(R.id.message_btn);
		app_activity_btn = (RelativeLayout) localSlidingMenu
				.findViewById(R.id.app_activity_btn);
		setting_btn = (RelativeLayout) localSlidingMenu
				.findViewById(R.id.setting_btn);

		feedback_btn = (RelativeLayout) localSlidingMenu
				.findViewById(R.id.feedback_btn);
		appstore_btn = (RelativeLayout) localSlidingMenu
				.findViewById(R.id.appstore_btn);
		special_offer_btn = (RelativeLayout) localSlidingMenu
				.findViewById(R.id.special_offer_btn);
		the_first_prize_btn = (RelativeLayout) localSlidingMenu
				.findViewById(R.id.the_first_prize_btn);

	}

	private void initListener() {
		cellphone_Login_btn.setOnClickListener(this);// 登录今日头条 按钮的点击事件监听
		qqzone_btn.setOnClickListener(this);
		sina_weibo_btn.setOnClickListener(this);// 微博登录 按钮的点击事件监听
		tv_More_login.setOnClickListener(this);
		setting_btn.setOnClickListener(this);// 设置 按钮的点击事件监听
		search_btn.setOnClickListener(this);
		favorite_btn.setOnClickListener(this);// 收藏 按钮的点击事件监听
		message_btn.setOnClickListener(this);
		app_activity_btn.setOnClickListener(this);// 活动 按钮的点击事件监听
		feedback_btn.setOnClickListener(this);
		appstore_btn.setOnClickListener(this);// 应用推荐 按钮的点击事件监听
		special_offer_btn.setOnClickListener(this);
		the_first_prize_btn.setOnClickListener(this);// 今日头彩 按钮的点击事件监听

	}

	/**
	 * 判断是哪个控件id的点击事件
	 */
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		// 设置
		case R.id.setting_btn:
			activity.startActivity(new Intent(activity, SettingsActivity.class));
			// 这是跳转时的动画，从右到左
			activity.overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
			break;

		// 搜索
		case R.id.search_btn:
			activity.startActivity(new Intent(activity, SearchActivity.class));
			activity.overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
			break;

		// 收藏
		case R.id.favorite_btn:
			activity.startActivity(new Intent(activity,
					CollectionActivity.class));
			activity.overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
			break;

		// 通知
		case R.id.message_btn:
			activity.startActivity(new Intent(activity,
					NoticeActivity.class));
			activity.overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
			break;

		// 活动
		case R.id.app_activity_btn:
			activity.startActivity(new Intent(activity,
					CampaignActivity.class));
			activity.overridePendingTransition(R.anim.slide_in_right,
					R.anim.slide_out_left);
			break;

		// 反馈
		case R.id.feedback_btn:

			break;

		// 应用推荐
		case R.id.appstore_btn:

			break;
		// 今日特卖
		case R.id.special_offer_btn:

			break;
		// 今日头彩
		case R.id.the_first_prize_btn:

			break;
		// 手机登录
		case R.id.cellphone_Login_btn:

			break;
		// QQ登录
		case R.id.qqzone_btn:

			break;
		// 微博登录
		case R.id.sina_weibo_btn:

			break;

		// 更多登录方式
		case R.id.tv_More_login:

			break;
		}

	}

}
