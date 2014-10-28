package com.headlineNews.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.headlineNews.R;
import com.headlineNews.adapter.NewsFragmentPagerAdapter;
import com.headlineNews.application.AppApplication;
import com.headlineNews.bean.Channel;
import com.headlineNews.bean.ChannelManage;
import com.headlineNews.fragment.NewsFragment;
import com.headlineNews.slidingmenu.lib.SlidingMenu;
import com.headlineNews.widget.ColumnHorizontalScrollView;
import com.headlineNews.widget.DrawerView;

/**
 * 本应用的主体
 * 
 * @author jack FragmentActivity 是 android.support.v4.app.FragmentActivity包下面的
 */
public class MainActivity extends FragmentActivity {

	/** 上下文 */
	private Context mContext;
	/** 头部 的中间的loading */
	private ProgressBar head_progress;
	/** 头部 中间的刷新按钮 */
	private ImageView head_refresh;
	/** 头部 的左侧菜单 按钮 */
	private ImageView head_munu;
	/** 头部 的右侧菜单 按钮 */
	private ImageView head_more;

	/** 自定义scrollView 的父布局（相对布局） */
	RelativeLayout rl_column;
	/** 自定义HorizontalScrollView */
	private ColumnHorizontalScrollView mColumnHorizontalScrollView;
	/** 导航的标题（线性布局） */
	LinearLayout mRadioGroup_content;
	/** 导航左边的阴影（图片） */
	private ImageView shade_left;
	/** 导航右边的阴影（图片） */
	private ImageView shade_right;
	/** 右端更多选项（线性布局） */
	LinearLayout ll_more_columns;
	/** 更多频道（图片按钮） */
	private ImageView button_more_columns;
	/** 显示新闻的页面（ViewPager） */
	private ViewPager mViewPager;

	/** slidingMenu实例 */
	private SlidingMenu side_drawer;

	/** 屏幕宽度 */
	private int mScreenWidth;
	/** 导航栏上当前的新闻频道集合 */
	private List<Channel> userChannelList = new ArrayList<Channel>();
	/** 当前默认选择的频道 */
	private int selectedChannel = 0;
	/** 频道选项的 宽度 */
	private int channelItemWidth = 70;

	/** fragment 集合 **/
	private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
	/** 请求CODE */
	public final static int CHANNELREQUEST = 1;
	/** 调整返回的RESULTCODE */
	public final static int CHANNELRESULT = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// 获取屏幕的宽度
		initBase();
		// 初始化视图
		initView();
		initSlidingMenu();

		initListener();

	}

	/**
	 * 得到屏幕宽度
	 */
	private void initBase() {
		mContext = this;
		Display display = getWindowManager().getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		display.getMetrics(metrics);
		mScreenWidth = metrics.widthPixels;
	}

	/**
	 * 初始化视图
	 */
	private void initView() {

		head_progress = (ProgressBar) findViewById(R.id.head_progress);
		head_refresh = (ImageView) findViewById(R.id.head_refresh);
		head_munu = (ImageView) findViewById(R.id.head_munu);
		head_more = (ImageView) findViewById(R.id.head_more);
		rl_column = (RelativeLayout) findViewById(R.id.rl_column);
		mColumnHorizontalScrollView = (ColumnHorizontalScrollView) findViewById(R.id.mColumnHorizontalScrollView);
		mRadioGroup_content = (LinearLayout) findViewById(R.id.mRadioGroup_content);
		shade_left = (ImageView) findViewById(R.id.shade_left);
		shade_right = (ImageView) findViewById(R.id.shade_right);
		ll_more_columns = (LinearLayout) findViewById(R.id.ll_more_columns);
		button_more_columns = (ImageView) findViewById(R.id.button_more_columns);
		mViewPager = (ViewPager) findViewById(R.id.mViewPager);

		setChangelView();
	}

	/**
	 * 初始化SlidingMenu
	 */
	private void initSlidingMenu() {
		side_drawer = new DrawerView(this).initSlidingMenu();
	}

	/**
	 * 监听事件
	 */
	private void initListener() {
		button_more_columns.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent_channel = new Intent(getApplicationContext(),
						ChannelManageActivity.class);
				// 启动activity，从应用界面跳转到频道界面
				startActivity(intent_channel);
				// 这里是，当activity进行跳转的时候，中间用动画效果去实现，从右边到左边
				overridePendingTransition(R.anim.slide_in_right,
						R.anim.slide_in_left);

			}
		});

		head_munu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// System.out.println(side_drawer+"  ****  "+side_drawer.isMenuShowing());
				// side_drawer是滑动菜单，当点击 head_munu 这个按钮
				// 的时候就滑动到 SettingsActivity 设置界面
				if (side_drawer.isMenuShowing()) {
					side_drawer.showContent();

				} else {
					side_drawer.showMenu();
				}

				// side_drawer.toggle();//这个方法会自动判断你的菜单是要打开还是关闭
			}
		});

		setChangelView();
	}

	/**
	 * activity返回的结果码，
	 * 
	 * 如果等于请求码， 就调用“setChangelView()当栏目项发生改变的时候调用”这个方法 requestCode 请求
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case CHANNELREQUEST:
			if (resultCode == CHANNELRESULT) {

			}
			break;
		}
	}

	/**
	 * 当栏目项发生变化时候调用
	 * */
	private void setChangelView() {
		initColumnData();
		initTabColumn();
		initFragment();
	}

	/** 获取Column栏目 数据 */
	private void initColumnData() {
		userChannelList = ((ArrayList<Channel>) ChannelManage.getManage(
				AppApplication.getApp().getSQLHelper()).getUserChannel());
	}

	/**
	 * 初始化Column栏目项
	 * */
	private void initTabColumn() {
		// 移除线性布局中所有试图
		mRadioGroup_content.removeAllViews();
		int count = userChannelList.size();
		for (int i = 0; i < count; i++) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					channelItemWidth, LayoutParams.WRAP_CONTENT);
			params.leftMargin = 5;
			params.rightMargin = 5;
			TextView columnTextView = new TextView(this);
			columnTextView.setTextAppearance(this,
					R.style.top_category_scroll_view_item_text);
			columnTextView.setBackgroundResource(R.drawable.radio_buttong_bg);
			columnTextView.setGravity(Gravity.CENTER);
			columnTextView.setPadding(5, 5, 5, 5);
			columnTextView.setId(i);
			columnTextView.setText(userChannelList.get(i).CName);
			columnTextView.setTextColor(getResources().getColorStateList(
					R.color.top_category_scroll_text_color_day));
			if (selectedChannel == i) {
				columnTextView.setSelected(true);
			}
			mRadioGroup_content.addView(columnTextView, i, params);
			// 点击频道
			columnTextView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
						View localView = mRadioGroup_content.getChildAt(i);
						if (localView != v)
							localView.setSelected(false);
						else {
							localView.setSelected(true);
							mViewPager.setCurrentItem(i);
						}
					}

				}
			});
		}
	}

	/**
	 * 初始化Fragment
	 * */
	private void initFragment() {
		fragments.clear();// 清空
		int count = userChannelList.size();
		for (int i = 0; i < count; i++) {
			Bundle data = new Bundle();
			data.putString("text", userChannelList.get(i).CName);
			data.putInt("id", userChannelList.get(i).CId);
			NewsFragment newfragment = new NewsFragment();
			newfragment.setArguments(data);
			fragments.add(newfragment);
		}
		NewsFragmentPagerAdapter mAdapetr = new NewsFragmentPagerAdapter(
				getSupportFragmentManager(), fragments);
		// mViewPager.setOffscreenPageLimit(0);
		mViewPager.setAdapter(mAdapetr);
		mViewPager.setOnPageChangeListener(pageListener);
	}

	/**
	 * 选择的Column里面的Tab
	 * */
	private void selectTab(int tab_postion) {
		selectedChannel = tab_postion;
		for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
			View checkView = mRadioGroup_content.getChildAt(tab_postion);
			int k = checkView.getMeasuredWidth();
			int l = checkView.getLeft();
			int i2 = l + k / 2 - mScreenWidth / 2;
			// rg_nav_content.getParent()).smoothScrollTo(i2, 0);
			mColumnHorizontalScrollView.smoothScrollTo(i2, 0);
			// mColumnHorizontalScrollView.smoothScrollTo((position - 2) *
			// mItemWidth , 0);
		}
		// 判断是否选中
		for (int j = 0; j < mRadioGroup_content.getChildCount(); j++) {
			View checkView = mRadioGroup_content.getChildAt(j);
			boolean ischeck;
			if (j == tab_postion) {
				ischeck = true;
			} else {
				ischeck = false;
			}
			checkView.setSelected(ischeck);
		}
	}

	/**
	 * ViewPager切换监听方法
	 * */
	private OnPageChangeListener pageListener = new OnPageChangeListener() {

		@Override
		public void onPageScrollStateChanged(int state) {
		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
		}

		@Override
		public void onPageSelected(int position) {
			mViewPager.setCurrentItem(position);
			selectTab(position);
		}
	};
}
