package com.people.lyy.scan.activity;

import com.people.lyy.scan.R;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;

public class SplashActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		TextView tv_tips = (TextView) findViewById(R.id.tv_tips);
		PackageManager pm = this.getPackageManager();
		PackageInfo pi;
		try {
			pi = pm.getPackageInfo(this.getPackageName(), 0);
			tv_tips.setText("众易付(zPOS) V" + pi.versionName);

		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		new SplashTask().execute();
	}

	class SplashTask extends AsyncTask<Object, Object, Object> {

		@Override
		protected Object doInBackground(Object... arg0) {
			try {
				Thread.sleep(1500);
				return null;

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

		}

		@Override
		protected void onPostExecute(Object result) {

			Intent intent0 = new Intent(SplashActivity.this,
				LoginActivity.class);
			SplashActivity.this.startActivity(intent0);
			SplashActivity.this.finish();
		}

	}

}
