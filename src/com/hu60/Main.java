package com.hu60;

import java.util.ArrayList;
import java.util.List;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends FragmentActivity {
	private Bkfrag bkfrag;
	private TzListFrag tzListFrag;
	private ChatFrag chatFrag;
	private ArrayList<Fragment> fragmentList;
	ArrayList<String> titleList = new ArrayList<String>();
	// 通过pagerTabStrip可以设置标题的属性
	private PagerTabStrip pagerTabStrip;

	private PagerTitleStrip pagerTitleStrip;
	private ListView lv1;
	private MyAdapter adapter;
	private static final String TAG = "Main";
	private Handler mHandle, mHandler1;
	private ViewPager vp;
	private ChatListAdapter chatListAdapter;
	private List<View> views;
	private TextView[] texts;
	private int[] ids = { R.id.textView1, R.id.textView2, R.id.textView3 };
	private NotificationManager notificationManager;// 提醒用户网络异常
	private ConnectivityManager cmanager;
	private BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			// 提醒用户网络异常 2g/3g?
			NetworkInfo mobileInfo=cmanager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			NetworkInfo wifiInfo =cmanager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (!mobileInfo.isConnected()&&!wifiInfo.isConnected()) {
				Notification.Builder builder=new Notification.Builder(Main.this);
				builder.setContentTitle("提示信息");
				builder.setContentText("网络连接不可用");
				builder.setSmallIcon(R.drawable.ic_launcher);
				notificationManager.notify(1001, builder.build());
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		cmanager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		vp = (ViewPager) findViewById(R.id.vp);

		pagerTabStrip = (PagerTabStrip) findViewById(R.id.pagertab);
		// 设置下划线的颜色
		pagerTabStrip.setTabIndicatorColor(Color.argb(0, 45, 159, 215));
		// 设置背景的颜色
		pagerTabStrip.setBackgroundColor(getResources().getColor(android.R.color.white));

		// pagerTitleStrip=(PagerTitleStrip) findViewById(R.id.pagertab);
		// //设置背景的颜色
		// pagerTitleStrip.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));

		tzListFrag = new TzListFrag();
		bkfrag = new Bkfrag();
		chatFrag = new ChatFrag();

		fragmentList = new ArrayList<Fragment>();
		fragmentList.add(tzListFrag);
		fragmentList.add(bkfrag);
		fragmentList.add(chatFrag);

		titleList.add("帖子列表");
		titleList.add("板块列表");
		titleList.add("聊天室 ");

		vp.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
		// m_vp.setOffscreenPageLimit(2);
	}

	public class MyViewPagerAdapter extends FragmentPagerAdapter {
		public MyViewPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			return fragmentList.get(arg0);
		}

		@Override
		public int getCount() {
			return fragmentList.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return titleList.get(position);
		}

	}
	// 注册广播
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		IntentFilter filter = new IntentFilter();
		filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(receiver, filter);
	}

	// 卸载广播
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (receiver != null) {
			unregisterReceiver(receiver);
		}
	}
	// initView();
	// initText();
	// }

	// private void initView() {
	// LayoutInflater inflater = LayoutInflater.from(this);
	// views = new ArrayList<View>();
	// views.add(inflater.inflate(R.layout.one, null));
	// views.add(inflater.inflate(R.layout.two, null));
	// views.add(inflater.inflate(R.layout.three, null));
	// vpAdater = new ViewPagerAdapter(views, this);
	// vp = (ViewPager) findViewById(R.id.vp);
	// lv = (PullToRefreshListView) views.get(0).findViewById(R.id.lv);
	// mHandle = new Handler() {
	// @Override
	// public void handleMessage(Message msg) {
	// TzList data = (TzList) msg.getData().getSerializable(
	// MyAdapter.BUNDLE_KEY_LIDATA);
	// Intent i = new Intent(Main.this, TzIn.class);
	// i.putExtra("tzid", data.getId());
	// startActivity(i);
	// }
	//
	// };

	// HttpTask task = new HttpTask();
	// task.setTaskHandler(new HttpTaskHandler() {
	// public void taskSuccessful(String json) {
	// try {
	// List<TzList> tz = JsonTools.getTz(json);
	// adapter = new MyAdapter(tz, mHandle, Main.this);
	// Log.i(TAG, adapter.toString());
	// lv.setAdapter(adapter);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	// public void taskFailed() {
	// }
	// });
	// task.execute("http://133.130.53.62/wap/0wap/m.php/api.bbs.json?json=compact&type=tzlist&bkid=0&order=hftime&offset=0&size=20");

	// vp.setAdapter(vpAdater);
	// vp.setOnPageChangeListener(this);
	// }
	//
	// private void initText() {
	// texts = new TextView[3];
	// for (int i = 0; i < 3; i++) {
	// texts[i] = (TextView) findViewById(ids[i]);
	// }
	// texts[0].setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// // TODO Auto-generated method stub
	// vp.setCurrentItem(0);
	// }
	// });
	// texts[1].setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// // TODO Auto-generated method stub
	// vp.setCurrentItem(1);
	// }
	// });
	// texts[2].setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// // TODO Auto-generated method stub
	// vp.setCurrentItem(2);
	// }
	// });
	// }
	//
	// @Override
	// public void onPageScrollStateChanged(int arg0) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void onPageScrolled(int arg0, float arg1, int arg2) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void onPageSelected(int arg0) {
	// // TODO Auto-generated method stub
	// for (int i = 0; i < 3; i++) {
	// if (arg0 == i) {
	// texts[i].setTextColor(Color.GREEN);
	// } else {
	// texts[i].setTextColor(Color.BLACK);
	// }
	// }
	// if (arg0 == 2) {
	// lv1 = (ListView) views.get(2).findViewById(R.id.lv1);
	// mHandler1 = new Handler() {
	// @Override
	// public void handleMessage(Message msg) {
	// // TzList data = (TzList) msg.getData().getSerializable(
	// // MyAdapter.BUNDLE_KEY_LIDATA);
	// // Intent i = new Intent(Main.this, TzIn.class);
	// // i.putExtra("tzid", data.getId());
	// // startActivity(i);
	// }
	//
	// };
	// HttpTask taskchat = new HttpTask();
	// taskchat.setTaskHandler(new HttpTaskHandler() {
	// public void taskSuccessful(String json) {
	// try {
	// List<ChatList> chat = JsonTools.getChatList(json);
	// chatListAdapter = new ChatListAdapter(chat, mHandler1,
	// Main.this);
	// Log.i(TAG, chatListAdapter.toString());
	// lv1.setAdapter(chatListAdapter);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	// public void taskFailed() {
	// }
	// });
	// taskchat.execute("http://133.130.53.62//wap/0wap/m.php/api.chat.php?type=chatlist");
	// }
	// }

	//
	// @Override
	// protected void onCreate(Bundle savedInstanceState) {
	// // TODO Auto-generated method stub
	// super.onCreate(savedInstanceState);
	// setContentView(R.layout.main);
	// lv = (ListView) findViewById(R.id.lv);
	// mHandle = new Handler() {
	// @Override
	// public void handleMessage(Message msg) {
	// TzList data = (TzList) msg.getData().getSerializable(
	// MyAdapter.BUNDLE_KEY_LIDATA);
	// Intent i = new Intent(Main.this, TzIn.class);
	// i.putExtra("tzid", data.getId());
	// startActivity(i);
	// }
	//
	// };
	// // tv = (TextView) findViewById(R.id.textView1);
	// // User user = (User) getIntent().getSerializableExtra("account");
	// // tv.setText(String.format("user msg %s id %s", user.getErrmsg(),
	// // user.getSid()));
	// HttpTask task = new HttpTask();
	// task.setTaskHandler(new HttpTaskHandler() {
	// public void taskSuccessful(String json) {
	// try {
	// List<TzList> tz = JsonTools.getTz(json);
	// adapter = new MyAdapter(tz, mHandle, Main.this);
	// Log.i(TAG, adapter.toString());
	// lv.setAdapter(adapter);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	// public void taskFailed() {
	// }
	// });
	// task.execute("http://133.130.53.62/wap/0wap/m.php/api.bbs.json?json=compact&type=tzlist&bkid=0&order=hftime&offset=0&size=20");
	// }

	long waitTime = 2000;
	long touchTime = 0;

	@Override
	public void onBackPressed() {
		long currentTime = System.currentTimeMillis();
		if ((currentTime - touchTime) >= waitTime) {
			Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
			touchTime = currentTime;
		} else {
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			android.os.Process.killProcess(android.os.Process.myPid());
		}
	}
}
