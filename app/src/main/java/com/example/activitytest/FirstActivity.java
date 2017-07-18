package com.example.activitytest;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class FirstActivity extends BaseActivity {
	private File zipfile;
	private long mDownLoadId;
	private CompleteReceiver localReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		Log.d("FirstActivity", this.toString());
		Log.d("FirstActivity", "Task id is " + getTaskId());
		//requestWindowFeature(Window.FEATURE_NO_TITLE);  //是否展示titlebar
		setContentView(R.layout.first_layout);
		Button button1 = (Button) findViewById(R.id.button_1);
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
//				intent.putExtra("param1", "data1");
//				intent.putExtra("param2", "data2");
//				startActivity(intent);
				if(mDownLoadId>0){
					SecondActivity.actionStart(FirstActivity.this, "data1", "data2");
				}
			}
		});

		/*
		* 注册下载完成广播
		* */
		localReceiver = new CompleteReceiver();
		registerReceiver(localReceiver,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

		// 1.检查是否存在pat压缩包,存在则删除
		zipfile = new File(FileConstant.JS_BUNDLE_LOCAL_PATH);
		if(zipfile != null && zipfile.exists()) {
			zipfile.delete();
		}

		/*
		* 调用系统下载管理功能
		* */
		DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
		DownloadManager.Request request = new DownloadManager
				.Request(Uri.parse(FileConstant.JS_BUNDLE_REMOTE_URL));
		request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
		request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE| DownloadManager.Request.NETWORK_WIFI);
		request.setDestinationUri(Uri.parse("file://"+ FileConstant.JS_BUNDLE_LOCAL_PATH));
		mDownLoadId = downloadManager.enqueue(request);
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d("FirstActivity", "onRestart");
	}

	/**
	 * 下载完成后收到广播
	 */
	public class CompleteReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			long completeId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID,-1);
			if(completeId == mDownLoadId) {
				// 将下载好的patches文件与assets目录下的原index.android.bundle合并，得到新的
				// bundle文件
				Log.d("DownLoad", "complete");
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.add_item:
			Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
			break;
		case R.id.remove_item:
			Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show();
			break;
		default:
		}
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 1:
			if (resultCode == RESULT_OK) {
				String returnedData = data.getStringExtra("data_return");
				Log.d("FirstActivity", returnedData);
			}
			break;
		default:
		}
	}

}
