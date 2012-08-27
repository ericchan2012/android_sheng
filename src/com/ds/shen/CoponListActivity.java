package com.ds.shen;

import java.util.Arrays;
import java.util.LinkedList;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.ds.widget.PullToRefreshListView;
import com.ds.widget.PullToRefreshListView.OnRefreshListener;

public class CoponListActivity extends Activity implements OnClickListener {
	private LinkedList<String> mListItems;
	private PullToRefreshListView pullToRefreshList;
	private Button mTopRightButton; 
	private static final String TAG = "CoponListActivity";
	private static final int SELECT_CITY = 0;
	private String[] mStrings = { "Abbaye de Belloc",
			"Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu",
			"Airag", "Airedale", "Aisy Cendre", "Allgauer Emmentaler",
			"Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam",
			"Abondance", "Ackawi", "Acorn", "Adelost", "Affidelice au Chablis",
			"Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
			"Allgauer Emmentaler", "Abbaye de Belloc",
			"Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu",
			"Airag", "Airedale", "Aisy Cendre", "Allgauer Emmentaler" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coupon_list_layout);
		pullToRefreshList = (PullToRefreshListView) findViewById(R.id.coupon_time_list_view);
		findViews();
		init();
		mListItems = new LinkedList<String>();
		mListItems.addAll(Arrays.asList(mStrings));

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mListItems);
		pullToRefreshList.setAdapter(adapter);
		pullToRefreshList.setOnRefreshListener(new OnRefreshListener() {
			public void onRefresh() {
				// Do work to refresh the list here.
				new GetDataTask().execute();
			}
		});

	}
	
	private void init(){
		mTopRightButton.setVisibility(View.VISIBLE);
		mTopRightButton.setOnClickListener(this);
	}
	
	private void findViews(){
		mTopRightButton = (Button)findViewById(R.id.top_right_button);
	}
	

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.top_right_button:
			mTopRightButton.setText(R.string.default_city);
			startActivityForResult(new Intent(this,CityListActivity.class),SELECT_CITY);
			break;
		}
	}

	private class GetDataTask extends AsyncTask<Void, Void, String[]> {

		protected String[] doInBackground(Void... params) {
			// Simulates a background job.
			Log.i(TAG, "---doInBackground----");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				;
			}
			return mStrings;
		}

		protected void onPostExecute(String[] result) {
			Log.i(TAG, "---doInBackground----");
			mListItems.addFirst("Added after refresh...");

			// Call onRefreshComplete when the list has been refreshed.
			pullToRefreshList.onRefreshComplete();

			super.onPostExecute(result);
		}
	}
}
