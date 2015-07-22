package com.hu60;

import java.util.List;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.domain.HfList;
import com.tools.TimeChange;

public class TzInfoAdapter extends BaseAdapter {
	private List<HfList> lists;
	private Context context;

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
			convertView = LayoutInflater.from(context).inflate(R.layout.hflist,
					null);
			holder = new ViewHolder();
			holder.hfnr = (TextView) convertView.findViewById(R.id.hfnr);
			holder.uhfname = (TextView) convertView.findViewById(R.id.uhfname);
			holder.hftime = (TextView) convertView.findViewById(R.id.hftime);
			String html = lists.get(position).getNr();
			Spanned htmlSequence = Html.fromHtml(html);
			holder.hfnr.setText(htmlSequence);
			holder.uhfname.setText(lists.get(position).getUname());
			holder.hftime.setText(TimeChange
					.tc(lists.get(position).getHftime()));
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
			String html = lists.get(position).getNr();
			Spanned htmlSequence = Html.fromHtml(html);
			holder.hfnr.setText(htmlSequence);
			holder.uhfname.setText(lists.get(position).getUname());
			holder.hftime.setText(TimeChange
					.tc(lists.get(position).getHftime()));
		}
		return convertView;
	}

	private static class ViewHolder {
		TextView hfnr;
		TextView uhfname;
		TextView hftime;
	}
}
