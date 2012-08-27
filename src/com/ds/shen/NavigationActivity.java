package com.ds.shen;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class NavigationActivity extends TabActivity implements OnClickListener {
	private static final int RECOMMAND = 0;
	private static final int BRAND = 1;
	private static final int RANKING = 2;
	private static final int MINE = 3;
	private static final int FAVORITE = 4;

	RadioButton mRecommandBtn;
	RadioButton mBrandBtn;
	RadioButton mRankingBtn;
	RadioButton mFavoriteBtn;
	RadioButton mMineBtn;
	TabHost mTabHost;

	Intent mRecommandIntent;
	Intent mBrandIntent;
	Intent mRankingIntent;
	Intent mMineIntent;
	Intent mFavoriteIntent;

	private static final String TAB_SPEC_RECOMMAND = "recommand";
	private static final String TAB_SPEC_BRAND = "brand";
	private static final String TAB_SPEC_RANKING = "ranking";
	private static final String TAB_SPEC_MINE = "mine";
	private static final String TAB_SPEC_FAVORITE = "favorite";

	private TabSpec mRecTabSpec;
	private TabSpec mBrandTabSpec;
	private TabSpec mRankingTabSpec;
	private TabSpec mMineTabSpec;
	private TabSpec mFavoriteTabSpec;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.navigation_layout);
		initIntents();
		initTabHost();

	}

	private void initTabHost() {
		// TODO Auto-generated method stub
		mTabHost.addTab(mRecTabSpec);
		mTabHost.addTab(mBrandTabSpec);

		mTabHost.setCurrentTab(0);
	}

	private void initIntents() {
		mTabHost = getTabHost();
		mRecommandIntent = new Intent(this, RecommandActivity.class);
		mBrandIntent = new Intent(this, BrandListActivity.class);
		mRankingIntent = new Intent(this, BrandListActivity.class);
		mMineIntent = new Intent(this, BrandListActivity.class);
		mFavoriteIntent = new Intent(this, BrandListActivity.class);

		mRecTabSpec = mTabHost.newTabSpec(TAB_SPEC_RECOMMAND)
				.setIndicator("tab1").setContent(mRecommandIntent);
		mBrandTabSpec = mTabHost.newTabSpec(TAB_SPEC_BRAND)
				.setIndicator("tab2").setContent(mBrandIntent);

		mRecommandBtn = (RadioButton) findViewById(R.id.tab_coupon);
		mBrandBtn = (RadioButton) findViewById(R.id.tab_brand);
		mRankingBtn = (RadioButton) findViewById(R.id.tab_attention);
		mMineBtn = (RadioButton) findViewById(R.id.tab_favorite);
		mFavoriteBtn = (RadioButton) findViewById(R.id.tab_settings);
		mRecommandBtn.setOnClickListener(this);
		mBrandBtn.setOnClickListener(this);
		mRankingBtn.setOnClickListener(this);
		mFavoriteBtn.setOnClickListener(this);
		mMineBtn.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tab_coupon:
			mTabHost.setCurrentTab(RECOMMAND);
			break;
		case R.id.tab_brand:
			mTabHost.setCurrentTab(BRAND);
			break;
		case R.id.tab_attention:
			mTabHost.setCurrentTab(RANKING);
			break;
		case R.id.tab_favorite:
			mTabHost.setCurrentTab(MINE);
			break;
		case R.id.tab_settings:
			mTabHost.setCurrentTab(FAVORITE);
			break;
		}
	}
}
