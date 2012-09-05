package com.ds.shen;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ds.widget.HorizontalListView;

public class RankingActivity extends Activity {

	RadioButton leftRadioBtn;
	RadioButton rightRadioBtn;
	HorizontalListView hListView;
	private static String[] dataObjects = new String[] { "Text #1", "Text #2",
			"Text #3", "Text #1", "Text #2", "Text #3", "Text #1", "Text #2",
			"Text #3", "Text #1", "Text #2", "Text #3" };

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rank_inc_top);
		initViews();
	}

	private void initViews() {

		leftRadioBtn = (RadioButton) findViewById(R.id.top_left_radio);
		rightRadioBtn = (RadioButton) findViewById(R.id.top_right_radio);
		leftRadioBtn.setText(R.string.brand);
		rightRadioBtn.setText(R.string.shopping_mall);

		hListView = (HorizontalListView) findViewById(R.id.phoneTopFilterListView);
		hListView.setAdapter(mAdapter);

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
					R.layout.rank_item, null);
			TextView title = (TextView) retval
					.findViewById(R.id.phoneFilterText);
			title.setText(dataObjects[position]);

			return retval;
		}

	};
}
