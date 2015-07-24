package com.hu60;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.text.util.Linkify;
import android.text.util.Linkify.MatchFilter;
import android.text.util.Linkify.TransformFilter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.domain.Defs;
import com.domain.HfInfoHead;
import com.domain.HfList;
import com.domain.TzInfo;
import com.http.GetPostUtil;
import com.http.HttpTask;
import com.http.HttpTask.HttpTaskHandler;
import com.http.PictureTask;
import com.json.JsonTools;
import com.tools.MyListView;
import com.tools.TimeChange;

public class TzIn extends Activity {
	private TextView ftime, hcount, rcount, ftitle, fnr, ftname;
	private static final String TAG = "TzIn";
	private MyListView hflistview;
	private HfInfoHead hfInfoHead;
	// private Button loadBtn;
	private String tzid, bkid, tznr;
	private int offset;
	private List<HfList> hfList;
	private TzInfoAdapter tzInfoAdapter;
	// private EditText editText;
	// private Button hfbtn;
	ImageGetter imgGetter;
	private int screenwidth;
	private int screenheight;
	private ProgressBar process,process2;
	private TextView jiazaiing,jiazaiing2,withoutmoretips;
	private ScrollView scview;
	private LinearLayout linearLayout;

	public TzIn() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tzinfore);
		DisplayMetrics mDisplayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
		screenwidth = mDisplayMetrics.widthPixels;
		screenheight = mDisplayMetrics.heightPixels;
		scview = (ScrollView) findViewById(R.id.buuju);
		linearLayout = (LinearLayout) findViewById(R.id.buju1);
		process = (ProgressBar) findViewById(R.id.progressBar1);
		jiazaiing = (TextView) findViewById(R.id.jiazaiing);
		process2=(ProgressBar) findViewById(R.id.progressBar2);
		jiazaiing2=(TextView) findViewById(R.id.jiazaiing2);
		withoutmoretips=(TextView) findViewById(R.id.withoutmoretips);
		ftitle = (TextView) findViewById(R.id.fttitile);
		ftname = (TextView) findViewById(R.id.ftname);
		rcount = (TextView) findViewById(R.id.liulanc);
		hcount = (TextView) findViewById(R.id.plc);
		
		ftime = (TextView) findViewById(R.id.fttime);

		fnr = (TextView) findViewById(R.id.ftnr);
		fnr.setClickable(false);
		// loadBtn = (Button) findViewById(R.id.loadBtn);
		// hfbtn = (Button) findViewById(R.id.hfbtn);
		// editText = (EditText) findViewById(R.id.hfnredit);
		// hfbtn.setOnClickListener(this);
		// loadBtn.setOnClickListener(this);
		fnr.setMovementMethod(ScrollingMovementMethod.getInstance());
		Intent i = getIntent();
		tzid = i.getStringExtra("tzid");
		bkid = i.getStringExtra("bkid");
		String path = "http://133.130.53.62/wap/0wap/m.php/api.bbs.json?type=tz&tzid=" + tzid + "&parse=2";
		HttpTask task = new HttpTask();
		task.setTaskHandler(new HttpTaskHandler() {
			public void taskSuccessful(String json) {
				try {
					TzInfo tzInfo = JsonTools.getTzInfo(json);
					Log.i(TAG, tzInfo.toString());
					ftitle.setText(tzInfo.getTitle());
					ftname.setText(tzInfo.getUname());
					rcount.setText(tzInfo.getRdcount());
					hcount.setText(tzInfo.getHfcount());
					ftime.setText(TimeChange.tc(tzInfo.getFttime()));
					String html = tzInfo.getNr();
					imgGetter = new Html.ImageGetter() {
						public Drawable getDrawable(String source1) {
							String source = null;
							try {
								source = java.net.URLDecoder.decode(source1, "utf-8");
								source = "http://133.130.53.62" + source;
							} catch (UnsupportedEncodingException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							Log.i("RG", "url---?>>>" + source);
							try {
								Drawable drawable = new PictureTask().execute(source).get();
								// if (drawable.getIntrinsicWidth() >
								// screenwidth-40&&drawable.getIntrinsicWidth()
								// <50) {
								if (drawable.getIntrinsicWidth() > 50) {
									drawable.setBounds(0, 0, screenwidth - 40, drawable.getIntrinsicHeight()
											* (screenwidth - 40) / drawable.getIntrinsicWidth());
								} else {
									drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
											drawable.getIntrinsicHeight());

								}
								return drawable;
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								return null;
							} catch (ExecutionException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								return null;
							}
						}
					};

					Spanned htmlSequence = Html.fromHtml(html, imgGetter, null);
					Log.i(TAG, htmlSequence.toString());
					fnr.setText(htmlSequence);
					extractMention2Link(fnr);
					linearLayout.setVisibility(View.VISIBLE);
					scview.setVisibility(View.VISIBLE);
					jiazaiing2.setVisibility(View.VISIBLE);
					process2.setVisibility(View.VISIBLE);
					jiazaiing.setVisibility(View.GONE);
					process.setVisibility(View.GONE);
					// fnr.setMovementMethod(LinkMovementMethod.getInstance());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			public void taskFailed() {
			}
		});
		task.execute(path);

		hflistview = (MyListView) findViewById(R.id.hflistview);

		String path1 = "http://133.130.53.62/wap/0wap/m.php/api.bbs.json?type=hf&tzid=" + tzid
				+ "&parse=2&order=asc&offset=" + offset + "&size=5";
		HttpTask task1 = new HttpTask();
		task1.setTaskHandler(new HttpTaskHandler() {
			public void taskSuccessful(String json) {
				try {
					hfInfoHead = JsonTools.getHfInfoHead(json);
					hfList = JsonTools.getHfLists(json);
					if (hfList.size()==0) {
						withoutmoretips.setVisibility(View.VISIBLE);
					}
					tzInfoAdapter = new TzInfoAdapter(hfList, getApplicationContext());
					hflistview.setAdapter(tzInfoAdapter);
					process2.setVisibility(View.GONE);
					jiazaiing2.setVisibility(View.GONE);
//					setListViewHeightBasedOnChildren(hflistview);
//					scview.smoothScrollTo(0, 0);
					// fnr.setMovementMethod(LinkMovementMethod.getInstance());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			public void taskFailed() {
			}
		});
		task1.execute(path1);

	}
	/**
	* 动态设置ListView的高度
	* @param listView
	*/
	public static void setListViewHeightBasedOnChildren(ListView listView) { 
	    if(listView == null) return;

	    ListAdapter listAdapter = listView.getAdapter(); 
	    if (listAdapter == null) { 
	        // pre-condition 
	        return; 
	    } 

	    int totalHeight = 0; 
	    for (int i = 0; i < listAdapter.getCount(); i++) { 
	        View listItem = listAdapter.getView(i, null, listView); 
	        listItem.measure(0, 0); 
	        totalHeight += listItem.getMeasuredHeight(); 
	    } 

	    ViewGroup.LayoutParams params = listView.getLayoutParams(); 
	    params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1)); 
	    listView.setLayoutParams(params); 
	}
	// final Handler h = new Handler() {
	// @Override
	// public void handleMessage(Message msg) {
	// if (msg.what == 0x123) {
	// // show.setText(msg.obj.toString());
	// editText.setText("");
	// Toast.makeText(getApplicationContext(), msg.obj.toString(),
	// Toast.LENGTH_SHORT).show();
	// }
	// }
	// };

	/*
	 * @Override public void onClick(View v) { // TODO Auto-generated method
	 * stub switch (v.getId()) { case R.id.loadBtn: final String path1 =
	 * "http://133.130.53.62/wap/0wap/m.php/api.bbs.json?type=hf&tzid=" + tzid +
	 * "&parse=2&order=asc&offset=" + offset + "&size=5"; HttpTask task1 = new
	 * HttpTask(); task1.setTaskHandler(new HttpTaskHandler() { public void
	 * taskSuccessful(String json) { try { hfInfoHead =
	 * JsonTools.getHfInfoHead(json); //
	 * fnr.setMovementMethod(LinkMovementMethod.getInstance()); if
	 * (hfInfoHead.getCount().equals( String.valueOf(hfList.size()))) {
	 * Toast.makeText(getApplicationContext(), "没有更多回复了",
	 * Toast.LENGTH_SHORT).show(); } else { offset += 5; // String path1 = //
	 * "http://133.130.53.62/wap/0wap/m.php/api.bbs.json?type=hf&tzid=" // + s +
	 * "&parse=2&order=asc&offset=" + offset + // "&size=5"; HttpTask task12 =
	 * new HttpTask(); task12.setTaskHandler(new HttpTaskHandler() { public void
	 * taskSuccessful(String json) { try { hfInfoHead = JsonTools
	 * .getHfInfoHead(json); List<HfList> hfList1 = JsonTools .getHfLists(json);
	 * hfList.addAll(hfList.size(), hfList1);
	 * tzInfoAdapter.notifyDataSetChanged(); //
	 * fnr.setMovementMethod(LinkMovementMethod.getInstance()); } catch
	 * (Exception e) { e.printStackTrace(); } }
	 * 
	 * public void taskFailed() { } }); task12.execute(path1); } } catch
	 * (Exception e) { e.printStackTrace(); } }
	 * 
	 * public void taskFailed() { } }); task1.execute(path1);
	 * 
	 * break; case R.id.hfbtn: try { tznr = java.net.URLEncoder.encode(
	 * editText.getText().toString(), "utf-8"); } catch
	 * (UnsupportedEncodingException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } if (tznr.equals("")) { Toast.makeText(this,
	 * "回复不能为空", Toast.LENGTH_SHORT).show(); } else { new Thread( new
	 * AccessNetwork( "POST", "http://133.130.53.62/wap/read.php?id=bbs_xiehf",
	 * "tzid=" + tzid + "&bkid=" + bkid +
	 * "&sid=1zed8Kg5izHg5mm-RW9VcNciMAAA&nr=" + tznr +
	 * "&go=%E5%BF%AB%E9%80%9F%E5%9B%9E%E5%A4%8D", h)).start(); } break; } }
	 */
	public static void extractMention2Link(TextView v) {
		v.setAutoLinkMask(0);

		Pattern mentionsPattern = Pattern.compile("@(\\w+?)(?=\\W|$)(.)");
		String mentionsScheme = String.format("%s/?%s=", Defs.MENTIONS_SCHEMA, Defs.PARAM_UID);
		Linkify.addLinks(v, mentionsPattern, mentionsScheme, new MatchFilter() {

			@Override
			public boolean acceptMatch(CharSequence s, int start, int end) {

				return s.charAt(end - 1) != '.';
			}

		}, new TransformFilter() {
			@Override
			public String transformUrl(Matcher match, String url) {
				Log.d(TAG, match.group(1));
				return match.group(1);
			}
		});
	}

}

class AccessNetwork implements Runnable {
	private String op;
	private String url;
	private String params;
	private Handler h;

	public AccessNetwork(String op, String url, String params, Handler h) {
		super();
		this.op = op;
		this.url = url;
		this.params = params;
		this.h = h;
	}

	@Override
	public void run() {
		Message m = new Message();
		m.what = 0x123;
		if (op.equals("GET")) {
			Log.i("iiiiiii", "发送GET请求");
			m.obj = GetPostUtil.sendGet(url, params);
			Log.i("iiiiiii", ">>>>>>>>>>>>" + m.obj);
		}
		if (op.equals("POST")) {
			Log.i("iiiiiii", "发送POST请求");
			m.obj = GetPostUtil.sendPost(url, params);
			Log.i("gggggggg", ">>>>>>>>>>>>" + m.obj);
		}
		h.sendMessage(m);
	}
}