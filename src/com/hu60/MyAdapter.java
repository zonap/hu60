package com.hu60;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.domain.TzList;

public class MyAdapter extends BaseAdapter {
	private List<TzList> lists;
	private Context context;

	// 点击索引：点击列表项；点击按钮；点击名字。

	// 记录数据列表
	// 记录Activity中接受消息的Handler
	// 关键字

	public MyAdapter(List<TzList> lists, Context context) {
		super();
		this.lists = lists;
		this.context = context;
	}

	public MyAdapter() {
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
			convertView = LayoutInflater.from(context).inflate(R.layout.tz,
					null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.nametv.setText(lists.get(position).getUname());
		holder.titletv.setText(lists.get(position).getTitle());
		holder.replytv.setText(lists.get(position).getHfcount());
		return convertView;
	}

	private static class ViewHolder {
		TextView titletv;
		TextView nametv;
		TextView replytv;

		public ViewHolder(View view) {
			titletv = (TextView) view.findViewById(R.id.Ttitle);
			nametv = (TextView) view.findViewById(R.id.uname);
			replytv = (TextView) view.findViewById(R.id.reply);
		}
	}
}
