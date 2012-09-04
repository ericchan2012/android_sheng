package com.ds.shen;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import com.ds.utility.Contants;
import com.ds.widget.ImageScrollView;
import com.ds.widget.ScrollImage;

public class RecommandActivity extends Activity {
	private static final int SELECT_CITY = 0;
	private ExpandableListView mExpandableListView;
	private int[] tags = new int[] { 0, 0, 0, 0, 0 };
	private String[] groups;
	private String[][] childs = new String[5][10];
	Gallery mGallery;
	GalleryAdapter mGalleryAdapter;
	ExpandableListAdapter mExpandableListAdapter;
	FlowIndicator mFlowIndicator;
	Timer mGalleryTimer;
	private Resources mRes;
	private Button mRightBtn;
	ScrollImage scroll;
	int whichScreen = -1;
	int[] res = new int[] { R.drawable.ic_gallery, R.drawable.ic_gallery,
			R.drawable.ic_gallery, R.drawable.ic_gallery,
			R.drawable.ic_gallery, R.drawable.ic_gallery,
			R.drawable.ic_gallery, R.drawable.ic_gallery, R.drawable.ic_gallery };
	View imageScrollView;
	final Handler scrollHandler = new Handler();
	boolean isRunning = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recommand_activity);
		initGroups();
		prepareView();
	}

	@Override
	protected void onResume() {
		super.onResume();
		isRunning = true;
	}

	@Override
	protected void onStop() {
		super.onStop();
		isRunning = false;
	}

	@Override
	protected void onPause() {
		super.onPause();
		isRunning = false;
	}

	private void initGroups() {
		mRes = getResources();
		// groups = new
		// String[]{mRes.getString(R.string.femal),mRes.getString(R.string.),mRes.getString(R.string.drink),mRes.getString(R.string.entertament),mRes.getString(R.string.jiaju)};
		groups = new String[] { "同步剧场", "奇艺出品", "热播电影", "3月片花速递", "动漫乐园" };

		mRightBtn = (Button) findViewById(R.id.top_right_button);
		mRightBtn.setVisibility(View.VISIBLE);
		mRightBtn.setText(R.string.default_city);
		mRightBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(RecommandActivity.this,
						CityListActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString(Contants.DEFAULT_CITY, mRightBtn.getText()
						.toString());
				intent.putExtras(bundle);
				startActivityForResult(intent, SELECT_CITY);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case SELECT_CITY:
				if (data != null) {
					Bundle bundle = data.getExtras();
					String city = bundle.getString(Contants.SELECT_CITY);
					mRightBtn.setText(city);
				}
				break;
			}
		}
	}

	private void startScrollImage() {
		imageScrollView = LayoutInflater.from(this).inflate(
				R.layout.image_scroll_view, null);
		scroll = (ScrollImage) imageScrollView.findViewById(R.id.simage1);
		for (int i = 0; i < res.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setImageResource(res[i]);
			scroll.setImageResource(imageView);
		}
		scroll.setPageControlView();
		Thread t = new Thread() {
			public void run() {
				while (isRunning) {
					scrollHandler.post(new Runnable() {
						public void run() {
							scroll.startScroll(whichScreen);
						}
					});
					whichScreen++;
					if (whichScreen > (res.length - 1)) {
						whichScreen = 0;
					}
					try {
						sleep(5000);
					} catch (InterruptedException e) {
					}
				}
			}
		};
		t.start();
	}

	private void prepareView() {
		startScrollImage();
		mGalleryAdapter = new GalleryAdapter(this);
		mExpandableListView = (ExpandableListView) findViewById(R.id.expandableListView1);
		mExpandableListAdapter = new ExpandableListAdapter();
		mExpandableListView.addHeaderView(imageScrollView);
		mExpandableListView.setAdapter(mExpandableListAdapter);
		// set the expandable auto expand
		for (int i = 0; i < groups.length; i++) {
			mExpandableListView.expandGroup(i);
		}
		mExpandableListView
				.setOnGroupExpandListener(new OnGroupExpandListener() {

					public void onGroupExpand(int arg0) {
						// tags[arg0] = 1;
					}
				});
		// hide the group click event
		mExpandableListView.setOnGroupClickListener(new OnGroupClickListener() {

			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				return true;
			}
		});
		mExpandableListView
				.setOnGroupCollapseListener(new OnGroupCollapseListener() {
					public void onGroupCollapse(int arg0) {
						tags[arg0] = 0;
					}
				});
	}

	private class ExpandableListAdapter extends BaseExpandableListAdapter {

		public ExpandableListAdapter() {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 10; j++) {
					childs[i][j] = "child" + i + "_" + j;
				}
			}
		}

		public String getChild(int groupPosition, int childPosition) {
			return childs[groupPosition][childPosition];
		}

		public long getChildId(int groupPosition, int childPosition) {
			return 0;
		}

		public View getChildView(int arg0, int arg1, boolean arg2, View view,
				ViewGroup arg4) {
			if (view == null) {
				view = LayoutInflater.from(RecommandActivity.this).inflate(
						R.layout.list_child_item, null);
				Gallery gallery = (Gallery) view.findViewById(R.id.imageView1);
				gallery.setAdapter(mGalleryAdapter);
			}
			return view;
		}

		public int getChildrenCount(int arg0) {
			return 1;
		}

		public Object getGroup(int groupPosition) {
			return groups[groupPosition];
		}

		public int getGroupCount() {
			return groups.length;
		}

		public long getGroupId(int arg0) {
			return arg0;
		}

		class GroupHolder {
			ImageView img;
			TextView title;
		}

		public View getGroupView(int arg0, boolean arg1, View arg2,
				ViewGroup arg3) {
			GroupHolder groupHolder;
			if (arg2 == null) {
				arg2 = LayoutInflater.from(RecommandActivity.this).inflate(
						R.layout.list_group_item, null);
				groupHolder = new GroupHolder();
				// groupHolder.img = (ImageView)
				// arg2.findViewById(R.id.tag_img);
				groupHolder.title = (TextView) arg2
						.findViewById(R.id.title_view);
				arg2.setTag(groupHolder);
			} else {
				groupHolder = (GroupHolder) arg2.getTag();
			}
			// if (tags[arg0] == 0) {
			// groupHolder.img
			// .setImageResource(R.drawable.list_indecator_button);
			// } else {
			// groupHolder.img
			// .setImageResource(R.drawable.list_indecator_button_down);
			// }
			groupHolder.title.setText(groups[arg0]);

			return arg2;
		}

		public boolean hasStableIds() {
			return true;
		}

		public boolean isChildSelectable(int arg0, int arg1) {
			return true;
		}

	}

}
