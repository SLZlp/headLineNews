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
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
 * @author jack
 *  FragmentActivity 是 android.support.v4.app.FragmentActivity包下面的
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
	private int channelItemWidth = 60;

	/**fragment 集合**/
	private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
	/** 请求CODE */
	public final static int CHANNELREQUEST = 1;
	/** 调整返回的RESULTCODE */
	public final static int CHANNELRESULT = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//获取屏幕的宽度
		initBase();
		//初始化视图
		initView();
		initSlidingMenu();

		initListener();

	}

	private void initListener() {
		
		/**
		 * 跳转到更多频道的 按钮
		 */
		button_more_columns.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent_channel = new Intent(getApplicationContext(),
						ChannelManageActivity.class);
				startActivityForResult(intent_channel, CHANNELREQUEST);
				overridePendingTransition(R.anim.slide_in_right,
						R.anim.slide_out_left);

			}
		});
		
		/**
		 * 就是头部左边上面那个有个头像的图片 点一下  就跳到slidingmemu 
		 */
		head_munu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (side_drawer.isMenuShowing()) {
					side_drawer.showContent();
				} else {
					side_drawer.showMenu();
				}
			}
		});
		
		
		//这个先别做
		/*head_more.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (side_drawer.isSecondaryMenuShowing()) {
					side_drawer.showContent();
				} else {
					side_drawer.showSecondaryMenu();
				}
			}
		});*/
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		/**
		 * 这里其实 也是写死了的  滑动的时候 当回应码等于 10 的 时候 就重新setChangelView();
		 */
		switch (requestCode) {
		case CHANNELREQUEST:
			if (resultCode == CHANNELRESULT) {
				setChangelView();
			}
			break;
		}
		
		super.onActivityResult(requestCode, resultCode, data);
		
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
		//移除线性布局中所有试图
		mRadioGroup_content.removeAllViews();
		int count = userChannelList.size();
		mColumnHorizontalScrollView.setParam(this, mScreenWidth,
				mRadioGroup_content, shade_left, shade_right, ll_more_columns,
				rl_column);
		for (int i = 0; i < count; i++) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					channelItemWidth, LayoutParams.WRAP_CONTENT);
			params.leftMargin = 5;
			params.rightMargin = 5;
			// TextView localTextView = (TextView)
			// mInflater.inflate(R.layout.column_radio_item, null);
			TextView columnTextView = new TextView(this);
			columnTextView.setTextAppearance(this,
					R.style.top_category_scroll_view_item_text);
			// localTextView.setBackground(getResources().getDrawable(R.drawable.top_category_scroll_text_view_bg));
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
					
					//这个其实就是你滑动一下弹的这个弹土司
					Toast.makeText(getApplicationContext(),
							userChannelList.get(v.getId()).CName,
							Toast.LENGTH_SHORT).show();
				}
			});
			mRadioGroup_content.addView(columnTextView, i, params);
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

	private long mExitTime;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (side_drawer.isMenuShowing()
					|| side_drawer.isSecondaryMenuShowing()) {
				side_drawer.showContent();
			} else {
				if ((System.currentTimeMillis() - mExitTime) > 2000) {
					Toast.makeText(this, "在按一次退出", Toast.LENGTH_SHORT).show();
					mExitTime = System.currentTimeMillis();
				} else {
					finish();
				}
			}
			return true;
		}
		// 拦截MENU按钮点击事件，让他无任何操作
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
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
		/**记录滑到了第几个viewpager**/
		@Override
		public void onPageSelected(int position) {
			mViewPager.setCurrentItem(position);
			selectTab(position);
		}
	};
}
