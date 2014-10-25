package com.headlineNews.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

public class ColumnHorizontalScrollView extends HorizontalScrollView {

//	/** 传入整体布局 */
//	private View ll_content;
//	/** 传入更多栏目选择布局 */
//	private View ll_more;
//	/** 传入拖动栏布局 */
//	private View rl_column;
//	/** 左阴影图片 */
//	private ImageView leftImage;
//	/** 右阴影图片 */
//	private ImageView rightImage;
//	/** 屏幕宽度 */
//	private int mScreenWitdh = 0;
//	/** 父类的活动activity */
//	private Activity activity;

	/**
	 * 传入父类布局中的资源文件
	 * */
//	public void setParam(Activity activity, int mScreenWitdh, View ll_content,
//			ImageView leftImage, ImageView rightImage, View ll_more,
//			View rl_column) {
//		this.activity = activity;
//		this.mScreenWitdh = mScreenWitdh;
//		this.ll_content = ll_content;
//		this.leftImage = leftImage;
//		this.rightImage = rightImage;
//		this.ll_more = ll_more;
//		this.rl_column = rl_column;
//	}

	

	public ColumnHorizontalScrollView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public ColumnHorizontalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ColumnHorizontalScrollView(Context context) {
		super(context);
	}

}
