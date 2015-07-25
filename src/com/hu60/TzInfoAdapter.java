package com.hu60;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;
import android.text.Html.ImageGetter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.domain.HfList;
import com.http.PictureTask;
import com.tools.TimeChange;

public class TzInfoAdapter extends BaseAdapter {
	private List<HfList> lists;
	private Context context;
	// private ImageGetter imageGetter = new Html.ImageGetter() {
	// @SuppressLint("NewApi")
	// public Drawable getDrawable(String source1) {
	// String source = null;
	// try {
	// source = java.net.URLDecoder.decode(source1, "utf-8");
	// String s = source.substring(0, 4);
	// if (!s.equals("http")) {
	// source = "http://133.130.53.62" + source;
	// }
	//
	// } catch (UnsupportedEncodingException e1) {
	// e1.printStackTrace();
	// }
	// Log.i("RG", "url---?>>>" + source);
	// try {
	// Drawable drawable = new PictureTask().execute(source).get();
	// if (drawable == null) {
	// drawable = getResources().getDrawable(R.drawable.imagegetdefeat);
	// }
	// if (drawable.getIntrinsicWidth() > 50) {
	// drawable.setBounds(0, 0, screenwidth - 40,
	// drawable.getIntrinsicHeight() * (screenwidth - 40) /
	// drawable.getIntrinsicWidth());
	// } else {
	// drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
	// drawable.getIntrinsicHeight());
	//
	// }
	// return drawable;
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// return null;
	// } catch (ExecutionException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// return null;
	// }
	// }
	// };

	public TzInfoAdapter(List<HfList> lists, Context context) {
		super();
		this.lists = lists;
		this.context = context;
	}

	public TzInfoAdapter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lists.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.hflist, null);
			holder = new ViewHolder();
			holder.hfnr = (TextView) convertView.findViewById(R.id.hfnr);
			holder.uhfname = (TextView) convertView.findViewById(R.id.uhfname);
			holder.hftime = (TextView) convertView.findViewById(R.id.hftime);
			holder.hfnum = (TextView) convertView.findViewById(R.id.hfnum);
			String html = lists.get(position).getNr();

			Spanned htmlSequence = Html.fromHtml(html, null, null);
			holder.hfnr.setText(htmlSequence);
			holder.uhfname.setText(lists.get(position).getUname());
			holder.hftime.setText(TimeChange.tc(lists.get(position).getHftime()));
			holder.hfnum.setText("#" + (position + 1));
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
			String html = lists.get(position).getNr();
			Spanned htmlSequence = Html.fromHtml(html);
			holder.hfnr.setText(htmlSequence);
			holder.uhfname.setText(lists.get(position).getUname());
			holder.hftime.setText(TimeChange.tc(lists.get(position).getHftime()));
		}
		return convertView;
	}

	private static class ViewHolder {
		TextView hfnr;
		TextView uhfname;
		TextView hftime;
		TextView hfnum;
	}
}
