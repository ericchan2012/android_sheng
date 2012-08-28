package com.ds.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ds.shen.R;
import com.ds.widget.ImageScrollView.ScrollToScreenCallback;

public class PageControlView extends LinearLayout implements
		ScrollToScreenCallback {
	/** Context对象 **/
	private Context context;
	/** 圆圈的数量 **/
	private int count;

	public PageControlView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	/** 回调函数 **/
	public void callback(int currentIndex) {
		generatePageControl(currentIndex);
	}

	/** 设置选中圆圈 **/
	public void generatePageControl(int currentIndex) {
		this.removeAllViews();

		for (int i = 0; i < this.count; i++) {
			ImageView iv = new ImageView(context);
			iv.setPadding(0, 0, 20, 0);
			if (currentIndex == i) {
				iv.setImageResource(R.drawable.page_indicator_focused);
			} else {
				iv.setImageResource(R.drawable.page_indicator);
			}
			this.addView(iv);
		}
	}

	/** 设置圆圈数量 **/
	public void setCount(int count) {
		this.count = count;
	}
}
