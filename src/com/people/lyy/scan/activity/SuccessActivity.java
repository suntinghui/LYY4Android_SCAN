package com.people.lyy.scan.activity;

import com.people.lyy.scan.R;
import com.people.lyy.scan.client.Constants;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

public class SuccessActivity extends BaseActivity {
	private TextView tv_cost = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_success);
		initview();
		setview();

	}

	public void initview() {
		tv_cost = (TextView) findViewById(R.id.tv_cost);
	}

	public void setview() {
		tv_cost.setText(Constants.COST + "");
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			if (event.getAction() == KeyEvent.ACTION_DOWN
					&& event.getRepeatCount() == 0) {
				finish();
			}
			return true;
		}
		return false;
	}

}
