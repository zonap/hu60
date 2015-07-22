package com.hu60;

import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ProgressBar;

import com.domain.TzList;
import com.http.HttpTask;
import com.http.HttpTask.HttpTaskHandler;
import com.hu60.AutoListView.OnLoadListener;
import com.hu60.AutoListView.OnRefreshListener;
import com.json.JsonTools;
public class TzListFrag extends Fragment implements OnLoadListener,
		OnRefreshListener {
	private View mMainView;
	private AutoListView tzlv;
	private MyAdapter adapter;
	private List<TzList> tz;
	private TextView tips;
	private int offset;
	private ProgressBar process;
	public TzListFrag() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		LayoutInflater inflater = getActivity().getLayoutInflater();
		mMainView = inflater.inflate(R.layout.tzfrag, (ViewGroup) getActivity()
				.findViewById(R.id.vp), false);
		tzlv = (AutoListView) mMainView.findViewById(R.id.tzlv);
		process=(ProgressBar) mMainView.findViewById(R.id.progressBar1);
		tips=(TextView) mMainView.findViewById(R.id.tips);
		tzlv.setOnRefreshListener(this);
		tzlv.setOnLoadListener(this);
		tzlv.setFocusableInTouchMode(true);
		tzlv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getActivity(), TzIn.class);
				System.out.println(position);
				if (position != 0 && position != tz.size() + 1) {
					i.putExtra("tzid", tz.get(position - 1).getId());
					i.putExtra("bkid", tz.get(position - 1).getBkid());
					startActivityForResult(i, 0);
					getActivity().overridePendingTransition(R.anim.push_left_in,
							R.anim.push_left_out);
				}
			}
		});
		initView();
		
	}

	private void initView() {
		offset = 0;
		String path="http://133.130.53.62/wap/0wap/m.php/api.bbs.json?json=compact&type=tzlist&bkid=0&order=fttime&offset="
				+ offset + "&size=15";
		Log.i("path", path);
		HttpTask task = new HttpTask();
		task.setTaskHandler(new HttpTaskHandler() {
			public void taskSuccessful(String json) {
				try {
					tz = null;
					tz = JsonTools.getTz(json);
					adapter = new MyAdapter(tz, getActivity());
					tzlv.setAdapter(adapter);
					tips.setVisibility(View.GONE);
					process.setVisibility(View.GONE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			public void taskFailed() {
			}
		});
		task.execute(path);

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

	@Override
	public void onRefresh() {
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				// 获取最新数据
				initView();
				if (tz != null) {
					// 通知listview 刷新数据完毕；
					tzlv.onRefreshComplete();
				}
			}
		}, 2000);
	}

	@Override
	public void onLoad() {
		offset += 15;
		String path = "http://133.130.53.62/wap/0wap/m.php/api.bbs.json?json=compact&type=tzlist&bkid=0&order=fttime&offset="
				+ offset + "&size=15";
		Log.i("path", path);
		HttpTask task = new HttpTask();
		task.setTaskHandler(new HttpTaskHandler() {
			public void taskSuccessful(String json) {
				try {
					List<TzList> tz1 = null;
					tz1 = JsonTools.getTz(json);
					tz.addAll(offset, tz1);
					tzlv.setResultSize(tz.size());
					adapter.notifyDataSetChanged();
					tzlv.onLoadComplete();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			public void taskFailed() {
			}
		});
		task.execute(path);
	}
}
