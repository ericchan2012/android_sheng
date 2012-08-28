package com.ds.shen;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ds.utility.Contants;

public class CityListActivity extends Activity {

	private Button mTopLeftBtn;
	private TextView mTitleView;
	private Intent mIntent;
	private String mCityName;
	private TextView mCurrentCity;
	private ListView mHotCityListView;
	private MyListAdapter mHotCityAdapter;
	private List<String> mHotCityList;
	private Resources mRes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.city_layout);
		mIntent = getIntent();
		mRes = getResources();
		initHeader();
		initDefaultCity();
		initHotCity();
	}

	private void initHotCity() {
		String[] hotcity = mRes.getStringArray(R.array.hotcity);
		mHotCityList = Arrays.asList(hotcity);
		mHotCityListView = (ListView) findViewById(R.id.city_list_view);
		mHotCityAdapter = new MyListAdapter(this);
		mHotCityListView.setAdapter(mHotCityAdapter);
		mHotCityListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				if(position != mHotCityList.size()){
					ListView listview = (ListView)adapterView;
					String city = (String)listview.getItemAtPosition(position);
					mCurrentCity.setText(city);
				}
			}
		});
	}

	private void initDefaultCity() {
		if (mIntent != null) {
			mCityName = mIntent.getExtras().getString(Contants.DEFAULT_CITY);
		}
		mCurrentCity = (TextView) findViewById(R.id.current_city);
		mCurrentCity.setText(mCityName);
	}

	private void initHeader() {
		mTopLeftBtn = (Button) findViewById(R.id.top_left_btton);
		mTitleView = (TextView) findViewById(R.id.module_title_text_view);
		mTitleView.setText(R.string.city_list_title);
		mTopLeftBtn.setVisibility(View.VISIBLE);
		mTopLeftBtn.setText(R.string.cancel);
		mTopLeftBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				CityListActivity.this.finish();
			}
		});
	}

	class MyListAdapter extends BaseAdapter {
		private Context context;// 用于接收传递过来的Context对象
		private LayoutInflater mInflater;

		public MyListAdapter(Context context) {
			super();
			this.context = context;
			mInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.widget.Adapter#getCount()
		 */
		public int getCount() {
			return mHotCityList.size() + 1;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.widget.Adapter#getItem(int)
		 */
		public Object getItem(int position) {
			return mHotCityList.get(position);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.widget.Adapter#getItemId(int)
		 */
		public long getItemId(int position) {
			return position;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.widget.Adapter#getView(int, android.view.View,
		 * android.view.ViewGroup)
		 */
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.city_list_adapter,
						null);
			}
			TextView tv = (TextView) convertView.findViewById(R.id.city);
			if (position == 0) {
				tv.setText(mHotCityList.get(position));
				tv.setBackgroundResource(R.drawable.list_item_top_bg);
			} else if (position == mHotCityList.size()) {
				tv.setText(R.string.more_city);
				tv.setBackgroundResource(R.drawable.list_item_bottom_bg);
			} else {
				tv.setText(mHotCityList.get(position));
				tv.setBackgroundResource(R.drawable.list_item_middle_bg);
			}
			return convertView;
		}

	}
}
