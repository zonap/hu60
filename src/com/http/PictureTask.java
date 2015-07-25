package com.http;

import java.net.URL;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import com.tools.ScreenUtils;

public class PictureTask extends AsyncTask<String, Void, Drawable> {
	Context context;

	public PictureTask() {

	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	Drawable ad(Drawable result) {
		return result;
	}

	@Override
	protected Drawable doInBackground(String... params) {
		// TODO Auto-generated method stub
		Drawable drawable = null;
		URL url;
		try {
			url = new URL(params[0]);
			Log.i("RG", "url--->>>" + url);
			drawable = Drawable.createFromStream(url.openStream(), "");
			// »ñÈ¡ÍøÂ·Í¼Æ¬
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		// drawable.setBounds(0, 0, ScreenUtils.getScreenWidth(context),
		// drawable.getIntrinsicHeight() / drawable.getIntrinsicWidth()
		// * ScreenUtils.getScreenWidth(context));
		// drawable.setBounds(0, 0, 200,
		// drawable.getIntrinsicHeight()*200/drawable.getIntrinsicWidth());
		Log.i("RG", "url---?>>>" + url);
		return drawable;
	}

	@Override
	protected void onPostExecute(Drawable result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		ad(result);
	}

}