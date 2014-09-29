package com.people.lyy.scan.activity;

import com.people.lyy.scan.R;
import com.people.lyy.scan.client.Constants;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SuccessActivity extends BaseActivity {
	private TextView tv_cost = null;
	private Button btn_over = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_success);
		initview();
		setview();

	}

	public void initview() {
		tv_cost = (TextView) findViewById(R.id.tv_cost);
		btn_over = (Button) findViewById(R.id.btn_over);
	}

	public void setview() {
		Intent intent = getIntent();
		String reponse = intent.getStringExtra("result"); 
		tv_cost.setText(reponse);
		btn_over.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		
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
