package com.http;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

/***
 * 
 * 下载图片类
 * 
 * @author XinYu
 * 
 */
public class DownLoadImage extends AsyncTask<String, integer, String> {

	String path = android.os.Environment.getExternalStorageDirectory()
			.getPath().toString()
			+ "/IMNG";
	Handler handler;

	public DownLoadImage() {
	}

	public DownLoadImage(Handler handler) {
		this.handler = handler;
	}

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		/*
		 * 该方法在OnpreExecute执行以后马上执行，改方法执行在后台线程当中，负责耗时的计算，
		 * 可以调用publishProcess方法来实时更新任务进度
		 */

		String fcf = params[0];
		Bitmap bitmap = null;
		Log.e("-------------------我的下载路径是：================-" + fcf,
				"--------------");
		File file = null;
		try {
			file = new File(path + String.valueOf(fcf.hashCode()));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (file.exists()) {
			bitmap = BitmapFactory.decodeFile(file.getPath());

			if (bitmap == null) {
				return null;
			}

		} else {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}

			try {
				file.createNewFile();
				URL url = new URL(fcf);
				HttpURLConnection httpURLConnection = (HttpURLConnection) url
						.openConnection();
				// httpURLConnection.setRequestMethod("GET");
				httpURLConnection.setConnectTimeout(500);

				InputStream is = httpURLConnection.getInputStream();

				RandomAccessFile randomAccessFile = new RandomAccessFile(file,
						"rwd");
				int index = -1;
				byte data[] = new byte[1024];

				while ((index = is.read(data)) != -1) {

					randomAccessFile.write(data, 0, index);

				}

				is.close();
				randomAccessFile.close();

				// bitmap = BitmapFactory.decodeFile(file.getPath());
				// listMap.add(bitmap);
				Log.e("----------------------下载王城--", "--------------");
				return file.getPath().toString();

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}

		return null;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected void onProgressUpdate(integer... values) {
		// TODO Auto-generated method stub

		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(String dd) {
		// TODO Auto-generated method stub
		if (dd == null) {
			Log.e("-------------" + "我下载失败了------", "------------");
			// 失败设置默认的图片
			// onDownlaodCellBackLinster.getFailResult(result);
			this.cancel(true);

		} else {
			handler.sendEmptyMessage(100);
			// onDownlaodCellBackLinster.getSuccessResult(result);
			this.cancel(true);
		}
		super.onPostExecute(dd);
	}

}