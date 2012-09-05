package com.ds.shen;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MineActivity extends Activity {
	private static String[] dataObjects = new String[] { "我的关注", "我的收藏",
			"历史记录", "关于我们", "Text #2", "Text #3", "Text #1", "Text #2",
			"Text #3", "Text #1", "Text #2", "Text #3" };

	ListView mineListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_inc_my);
		initViews();
	}

	private void initViews() {
		mineListView = (ListView) findViewById(R.id.phoneMyMainListView);
		mineListView.setAdapter(mAdapter);
	}

	private BaseAdapter mAdapter = new BaseAdapter() {

		public int getCount() {
			return dataObjects.length;
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			View retval = LayoutInflater.from(parent.getContext()).inflate(
					R.layout.phone_adapter_my_main, null);
			TextView title = (TextView) retval
					.findViewById(R.id.phoneMyMainText);
			title.setText(dataObjects[position]);

			return retval;
		}

	};

}
