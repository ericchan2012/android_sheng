package com.ds.widget;

import com.ds.shen.R;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ScrollImage extends RelativeLayout {
	LayoutInflater inflater;
	ImageScrollView imageScrollView;
	PageControlView pageControlView;

	public ScrollImage(Context context, AttributeSet attrs) {
		super(context, attrs);
		Log.i("ScrollImage", "----ScrollImage()---");
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.scroll_image, this);
		// addView(view);
		imageScrollView = (ImageScrollView) this
				.findViewById(R.id.myImageScrollView);
		pageControlView = (PageControlView) this
				.findViewById(R.id.myPageControlView);
	}

	public void startScroll(int whichScreen) {
		imageScrollView.scrollToScreen(whichScreen);
	}

	public void setImageResource(ImageView imageview) {
		imageScrollView.addView(imageview);
	}

	public void setPageControlView() {
		/** 设置圆圈的数量 **/
		pageControlView.setCount(imageScrollView.getChildCount());
		Log.i("ScrollImage", "----imageScrollView.getChildCount()---"
				+ imageScrollView.getChildCount());
		/** 初始化圆圈 **/
		pageControlView.generatePageControl(0);
		/** 设置视图切换回调函数实现 **/
		imageScrollView.setScrollToScreenCallback(pageControlView);
	}

}
