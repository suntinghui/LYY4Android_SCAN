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

public class BarCodeActivity extends Activity {
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
				// Constants.COST = et_money.getText().toString().trim();
//				Intent openCameraIntent = new Intent(BarCodeActivity.this, CaptureActivity.class);
//				if (mTask != null)
//					mTask.onCancelled();
//				mTask = new MyTask();
//				startActivity(openCameraIntent);
				
				Intent service = new Intent("com.people.sotp.lyyservice");
				startService(service);
			}
		});
	}

	// imView.setOnClickListener(new OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// mTask = new MyTask();
	// mTask.execute(imageUrl);
	// mTask.onCancelled();
	// }
	// });

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// ����ɨ�����ڽ�������ʾ��
		String scanResult = null;
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			scanResult = bundle.getString("result");
			// scanResult就是扫描二维码返回来的东西
			// resultTextView.setText(scanResult);
			mTask.execute(scanResult);
		}
	}

	private class MyTask extends AsyncTask<String, Integer, Bitmap> {
		String TAG = "AsyncTask";

		// onPreExecute����������ִ�к�̨����ǰ��һЩUI����
		@Override
		protected void onPreExecute() {
			Log.i(TAG, "onPreExecute() called");
		}

		// doInBackground�����ڲ�ִ�к�̨����,�����ڴ˷������޸�UI
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
				// conn.setDoOutput(true);//����java.io.FileNotFoundException
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

		// onProgressUpdate�������ڸ��½����Ϣ
		@Override
		protected void onProgressUpdate(Integer... progresses) {
			// resultTextView.setText("onProgressUpdate");
		}

		// onPostExecute����������ִ�����̨��������UI,��ʾ���
		// protected void onPostExecute(Bitmap bitmap) {
		// if (bitmap != null){
		// imView.setImageBitmap(bitmap);
		// }else{
		// resultTextView.setText("Get "+imageUrl+" error");
		// }
		// }

		// onCancelled����������ȡ��ִ���е�����ʱ���UI
		@Override
		protected void onCancelled() {
			Log.i(TAG, "onCancelled() called");
		}
	}

	@SuppressWarnings("unused")
	// private class TounchListener implements OnTouchListener{
	//
	// private PointF startPoint = new PointF();
	// private Matrix matrix = new Matrix();
	// private Matrix currentMaritx = new Matrix();
	//
	// private int mode = 0;//���ڱ��ģʽ
	// private static final int DRAG = 1;//�϶�
	// private static final int ZOOM = 2;//�Ŵ�
	// private float startDis = 0;
	// private PointF midPoint;//���ĵ�
	// public boolean onTouch(View v, MotionEvent event) {
	// switch (event.getAction() & MotionEvent.ACTION_MASK) {
	// case MotionEvent.ACTION_DOWN:
	// mode = DRAG;
	// currentMaritx.set(imView.getImageMatrix());//��¼ImageView���ڵ��ƶ�λ��
	// startPoint.set(event.getX(),event.getY());//��ʼ��
	// break;
	//
	// case MotionEvent.ACTION_MOVE://�ƶ��¼�
	// if (mode == DRAG) {//ͼƬ�϶��¼�
	// float dx = event.getX() - startPoint.x;//x���ƶ�����
	// float dy = event.getY() - startPoint.y;
	// matrix.set(currentMaritx);//�ڵ�ǰ��λ�û����ƶ�
	// matrix.postTranslate(dx, dy);
	//
	// } else if(mode == ZOOM){//ͼƬ�Ŵ��¼�
	// float endDis = distance(event);//�������
	// if(endDis > 10f){
	// float scale = endDis / startDis;//�Ŵ���
	// matrix.set(currentMaritx);
	// matrix.postScale(scale, scale, midPoint.x, midPoint.y);
	// }
	//
	//
	// }
	//
	// break;
	// case MotionEvent.ACTION_UP:
	// mode = 0;
	// break;
	// //����ָ�뿪��Ļ������Ļ���д���(��ָ)
	// case MotionEvent.ACTION_POINTER_UP:
	// mode = 0;
	// break;
	// //����Ļ���Ѿ��д��㣨��ָ��,����һ����ָѹ����Ļ
	// case MotionEvent.ACTION_POINTER_DOWN:
	// mode = ZOOM;
	// startDis = distance(event);
	//
	// if(startDis > 10f){//������ָ����������
	// midPoint = mid(event);
	// currentMaritx.set(imView.getImageMatrix());//��¼��ǰ�����ű���
	// }
	//
	// break;
	//
	//
	// }
	// imView.setImageMatrix(matrix);
	// return true;
	// }
	// }
	/**
	 * ����֮��ľ���
	 * @param event
	 * @return
	 */
	private static float distance(MotionEvent event) {
		// �����ߵľ���
		float dx = event.getX(1) - event.getX(0);
		float dy = event.getY(1) - event.getY(0);
		return FloatMath.sqrt(dx * dx + dy * dy);
	}

	/**
	 * ��������֮�����ĵ�ľ���
	 * 
	 * @param event
	 * @return
	 */
	private static PointF mid(MotionEvent event) {
		float midx = event.getX(1) + event.getX(0);
		float midy = event.getY(1) - event.getY(0);

		return new PointF(midx / 2, midy / 2);
	}
}
