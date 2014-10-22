package com.headlineNews.widget;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.headlineNews.R;
import com.headlineNews.activity.SettingActivity;
import com.headlineNews.slidingmenu.lib.SlidingMenu;
import com.headlineNews.slidingmenu.lib.SlidingMenu.OnClosedListener;

/**
 * 自定义SlidingMenu 测拉菜单类
 * 
 * @author susan
 * 
 */
public class DrawerView implements OnClickListener {
	private Activity activity;
	private SlidingMenu localSlidingMenu;
    /**相对布局 设置 页面**/
	private RelativeLayout setting_btn;


	public DrawerView(Activity activity) {
		super();
		this.activity = activity;
	}

	/**
	 * 初始化slidingMenu,设为左滑动
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
		//localSlidingMenu.toggle();// 动态判断自动关闭或开启SlidingMenu
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
		return localSlidingMenu;
	}

	private void initView() {
		/**找到 设置 控件 **/
		setting_btn = (RelativeLayout) localSlidingMenu.findViewById(R.id.setting_btn);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case 0:
			
			break;
		case 1:
			
			break;
			/**跳转到 设置页面**/
		case R.id.setting_btn:
			activity.startActivity(new Intent(activity,SettingActivity.class));
			activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			break;

		default:
			break;
		}

	}

	
	
}
