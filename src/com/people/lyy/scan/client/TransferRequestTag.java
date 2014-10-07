package com.people.lyy.scan.client;

import android.annotation.SuppressLint;
import java.util.HashMap;

@SuppressLint("UseSparseArrays")
public class TransferRequestTag {

	public static final int Login = 1;// 登录
	public static final int Consumer = 2; // 消费
	private static HashMap<Integer, String> requestTagMap = null;

	public static HashMap<Integer, String> getRequestTagMap() {
		if (null == requestTagMap) {
			requestTagMap = new HashMap<Integer, String>();

			requestTagMap.put(Login, Constants.ip + "");
			requestTagMap.put(Consumer, Constants.ip + "/verify/");
			
		}

		return requestTagMap;
	}

}
