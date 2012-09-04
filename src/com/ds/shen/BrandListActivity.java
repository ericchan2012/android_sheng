package com.ds.shen;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

public class BrandListActivity extends Activity {
	RadioButton leftRadioBtn;
	RadioButton rightRadioBtn;
	Button topRightBtn;
	RelativeLayout menuLayout;
	Animation menuAnimation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.brand_list_layout);
		initViews();
	}

	private void initViews() {
		menuAnimation= AnimationUtils.loadAnimation(this,R.anim.brand_menu_scale);
		
		leftRadioBtn = (RadioButton) findViewById(R.id.top_left_radio);
		rightRadioBtn = (RadioButton) findViewById(R.id.top_right_radio);
		leftRadioBtn.setText(R.string.brand);
		rightRadioBtn.setText(R.string.shopping_mall);

		menuLayout = (RelativeLayout)findViewById(R.id.brand_list_menu);
		
		topRightBtn = (Button) findViewById(R.id.top_right_button);
//		topRightBtn.setVisibility(View.VISIBLE);
		topRightBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				menuLayout.setVisibility(View.VISIBLE);
				menuLayout.startAnimation(menuAnimation);
			}
		});
	}
}
