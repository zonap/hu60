package com.hu60;

import java.util.List;

import com.domain.TzList;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.http.HttpTask;
import com.http.HttpTask.HttpTaskHandler;
import com.json.JsonTools;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;

public class ViewPagerAdapter extends PagerAdapter {
	private Context context;
	private List<View> views;

	public ViewPagerAdapter() {
		// TODO Auto-generated constructor stub
	}

	public ViewPagerAdapter(List<View> views, Context context) {
		this.views = views;
		this.context = context;
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager) container).removeView(views.get(position));
	}

	@Override
	public View instantiateItem(View container, int position) {
		// TODO Auto-generated method stub
//		((ViewPager) container).addView(views.get(position));
//
//		return views.get(position);
		final Context context = container.getContext();
		final PullToRefreshListView plv=(PullToRefreshListView) LayoutInflater.from(context).inflate(
				R.layout.one, (ViewPager) container, false);
	

		HttpTask task = new HttpTask();
		task.setTaskHandler(new HttpTaskHandler() {
			public void taskSuccessful(String json) {
				try {
					List<TzList> tz = JsonTools.getTz(json);
					MyAdapter adapter = new MyAdapter(tz,context);
					plv.setAdapter(adapter);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			public void taskFailed() {
			}
		});
		task.execute("http://133.130.53.62/wap/0wap/m.php/api.bbs.json?json=compact&type=tzlist&bkid=0&order=hftime&offset=0&size=20");
		((ViewPager) container).addView(plv, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		return plv;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return views.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return (arg0 == arg1);
	}

}
