package com.people.lyy.scan.activity;

import com.people.lyy.scan.R;
import com.people.lyy.scan.client.Constants;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class SuccessActivity extends BaseActivity {
	private TextView tv_cost = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_success);
		initview();
		setview();
		new SuccessTask().execute();
	}
	
	public class SuccessTask extends AsyncTask<Object, Object, Object>{
		@Override
		protected Object doInBackground(Object... params) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(Object result) {
			Intent intent = new Intent(SuccessActivity.this,BarCodeActivity.class);
			startActivity(intent);
			SuccessActivity.this.finish();
		}
	}
	
	public void initview(){
		tv_cost = (TextView) findViewById(R.id.tv_cost);
	}
	public void setview(){
		tv_cost.setText(Constants.COST+"");
	}
}
