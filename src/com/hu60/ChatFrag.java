package com.hu60;

import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.domain.ChatList;
import com.http.HttpTask;
import com.http.HttpTask.HttpTaskHandler;
import com.json.JsonTools;

public class ChatFrag extends Fragment {
	private ListView lv1;

	public ChatFrag() {
		// TODO Auto-generated constructor stub
	}

	private ChatListAdapter chatListAdapter;
	private View mMainView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = getActivity().getLayoutInflater();
		mMainView = inflater.inflate(R.layout.three, (ViewGroup) getActivity()
				.findViewById(R.id.vp), false);
		lv1 = (ListView) mMainView.findViewById(R.id.lv1);
		initview();
	}

	private void initview() {
//		mHandler1 = new Handler() {
//			@Override
//			public void handleMessage(Message msg) {
//				// TzList data = (TzList) msg.getData().getSerializable(
//				// MyAdapter.BUNDLE_KEY_LIDATA);
//				// Intent i = new Intent(Main.this, TzIn.class);
//				// i.putExtra("tzid", data.getId());
//				// startActivity(i);
//			}
//
//		};
		HttpTask taskchat = new HttpTask();
		taskchat.setTaskHandler(new HttpTaskHandler() {
			public void taskSuccessful(String json) {
				try {
					List<ChatList> chat = JsonTools.getChatList(json);
					chatListAdapter = new ChatListAdapter(chat, null,
							getActivity());
					lv1.setAdapter(chatListAdapter);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			public void taskFailed() {
			}
		});
		taskchat.execute("http://133.130.53.62//wap/0wap/m.php/api.chat.php?type=chatlist");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ViewGroup p = (ViewGroup) mMainView.getParent();
		if (p != null) {
			p.removeAllViewsInLayout();
		}
		return mMainView;
	}

}
