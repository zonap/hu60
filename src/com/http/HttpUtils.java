package com.http;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import android.transition.ChangeBounds;
import android.util.Log;

public class HttpUtils {

	public HttpUtils() {
		// TODO Auto-generated constructor stub
	}
	private static final String TAG="HttpUtils";

	public static String getJsonContent(String path) {
		try {
			URL url = new URL(path);
			HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
			urlcon.setConnectTimeout(5000);
			urlcon.setRequestMethod("GET");
			if (urlcon.getResponseCode() == 200) {
				InputStreamReader reader = new InputStreamReader(
						urlcon.getInputStream(), "utf-8");
				BufferedReader in = new BufferedReader(reader);
				StringBuffer buffer = new StringBuffer();
				String line = " ";
				try {
					while ((line = in.readLine()) != null) {
						buffer.append(line);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Log.i(TAG,buffer.toString());
				return buffer.toString();
			}
		} catch (Exception e) {
			Log.i(TAG,e.toString());
		}
		return "";
	}
}