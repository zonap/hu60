package com.http;

import android.os.AsyncTask;
import android.util.Log;

public class HttpTask extends AsyncTask<String, Integer, String> {
	private static final String TAG = "HttpTask";

	@Override
	protected String doInBackground(String... params) {
		// Performed on Background Thread
		String url = params[0];
		try {
			String json = new NetworkTool().getContentFromUrl(url);
			Log.i(TAG, json);
			return json;
		} catch (Exception e) {
			// TODO handle different exception cases
			e.printStackTrace();
			return null;
		}
	}

	@Override
	protected void onPostExecute(String json) {
		// Done on UI Thread
		if (json != null && json != "") {

			int i1 = json.indexOf("["), i2 = json.indexOf("{"), i = i1 > -1
					&& i1 < i2 ? i1 : i2;
			if (i > -1) {
				json = json.substring(i);
				taskHandler.taskSuccessful(json);
			} else {
				taskHandler.taskFailed();
			}
		} else {
			taskHandler.taskFailed();
		}
	}

	public static interface HttpTaskHandler {
		void taskSuccessful(String json);

		void taskFailed();
	}

	HttpTaskHandler taskHandler;

	public void setTaskHandler(HttpTaskHandler taskHandler) {
		this.taskHandler = taskHandler;
	}

}