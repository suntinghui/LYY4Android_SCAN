package com.people.lyy.scan.activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.people.lyy.scan.R;
import com.people.lyy.scan.client.Constants;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class BarCodeActivity extends BaseActivity {
	private View btn_scan;
	String imageUrl = "http://hiphotos.baidu.com/baidu/pic/item/7d8aebfebf3f9e125c6008d8.jpg";
	// ImageView imView;
	private MyTask mTask;
	String TAG = "BarCodeActivity";
	private EditText et_money = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.barcode);
		et_money = (EditText) findViewById(R.id.et_money);
		btn_scan = findViewById(R.id.btn_scan);
		// imView.setOnTouchListener(new TounchListener());

		btn_scan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(BarCodeActivity.this, CaptureActivity.class);
				intent.putExtra("money", et_money.getText().toString().trim());
				startActivity(intent);
			}
		});
	}

	private class MyTask extends AsyncTask<String, Integer, Bitmap> {
		String TAG = "AsyncTask";

		@Override
		protected void onPreExecute() {
			Log.i(TAG, "onPreExecute() called");
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			URL myFileUrl = null;
			Bitmap bitmap = null;
			HttpURLConnection conn = null;
			try {
				imageUrl = params[0];
				myFileUrl = new URL(params[0]);
			} catch (MalformedURLException e) {
				e.printStackTrace();
				return null;
			}
			try {
				conn = (HttpURLConnection) myFileUrl.openConnection();
				conn.setDoInput(true);
				conn.setConnectTimeout(1000);
				conn.setRequestMethod("GET");

				// conn.setReadTimeout(1000);
				conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
				conn.connect();
				InputStream is = conn.getInputStream();
				if (is == null) {
					Log.i(TAG, "getInputStream error");
				}
				bitmap = BitmapFactory.decodeStream(is);
				is.close();
			} catch (IOException e) {
				Log.i(TAG, e.getMessage());
				e.printStackTrace();
			} finally {
				if (conn != null) {
					conn.disconnect();
				}
			}

			return bitmap;
		}

		@Override
		protected void onProgressUpdate(Integer... progresses) {
			// resultTextView.setText("onProgressUpdate");
		}

		@Override
		protected void onCancelled() {
			Log.i(TAG, "onCancelled() called");
		}
	}

}
