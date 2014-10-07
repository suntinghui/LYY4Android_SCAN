package com.people.lyy.scan.client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;
import android.util.Xml;

public class ParseResponseXML {

	public static Object parseXML(int reqType, String responseStr) {
		Log.e("response:", responseStr);
		
		try {
			switch (reqType) {
			case TransferRequestTag.Login: // 登录
				return login(responseStr);
				
			case TransferRequestTag.Consumer:// 消费
				return consumer(responseStr);

			}

		}catch (Exception e) {
			e.printStackTrace();

		} 

		return null;
	}

	// 登录
	private static HashMap<String, Object> login(String str) {

		return null;
	}
	
	private static HashMap<String, String> consumer(String str){
		String[] ss = str.split("&");
		HashMap<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < ss.length; i++) {
			String[] tt = ss[i].split("=");
			if (tt.length == 2) {
				map.put(tt[0].trim(), tt[1].trim());
			} else {
				map.put(tt[0].trim(), "");
			}
		}

		return map;
	}

	
}
