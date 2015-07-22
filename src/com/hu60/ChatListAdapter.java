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

import com.domain.ChatList;
import com.hu60.R;

public class ChatListAdapter extends BaseAdapter {
	private List<ChatList> lists;
	private Context context;
	private static final String TAG = "MyAdapter";
	// �������������б�������ť��������֡�
	protected final static int CLICK_ITEM = 0;

	// ��¼�����б�
	// ��¼Activity�н�����Ϣ��Handler
	private Handler mHandle;
	// �ؼ���
	public final static String BUNDLE_KEY_LDATA = "ldata";

	public ChatListAdapter(List<ChatList> lists, Handler handle, Context context) {
		super();
		this.lists = lists;
		mHandle = handle;
		this.context = context;
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
			convertView = LayoutInflater.from(context).inflate(
					R.layout.chatinfo, null);
			holder = new ViewHolder();
			holder.chattitle = (TextView) convertView
					.findViewById(R.id.chattitle);
			holder.timetv = (TextView) convertView.findViewById(R.id.ltime);

			holder.chattitle.setText(lists.get(position).getName());
			holder.timetv.setText(lists.get(position).getTime());
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
			holder.chattitle.setText(lists.get(position).getName());
			holder.timetv.setText(lists.get(position).getTime());
		}
		convertView.setOnClickListener(new OnItemChildClickListener(CLICK_ITEM,
				position));
		Log.i(TAG, convertView.toString());
		return convertView;
	}

	private class OnItemChildClickListener implements View.OnClickListener {
		// ���������������Ӧǰ���CLICK_INDEX_xxx
		private int clickIndex;
		// ����б�λ��
		private int position;

		public OnItemChildClickListener(int clickIndex, int position) {
			this.clickIndex = clickIndex;
			this.position = position;
		}

		@Override
		public void onClick(View v) {
			// ����Message��������ݣ�ͨ��mHandle��ϵActivity���մ���
			Message msg = new Message();
			msg.what = clickIndex;
			msg.arg1 = position;
			Bundle b = new Bundle();
			b.putSerializable(BUNDLE_KEY_LDATA, lists.get(position));
			msg.setData(b);
			mHandle.sendMessage(msg);
		}

	}

	private static class ViewHolder {
		TextView chattitle;
		TextView timetv;
	}

}
