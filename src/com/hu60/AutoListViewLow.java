package com.hu60;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

/**
 * @author SunnyCoffee
 * @create 2013-10-24
 * @version 1.0
 * @desc �Զ���Listview������ˢ��,�������ظ���
 */

public class AutoListViewLow extends ListView implements OnScrollListener {

	// ���ֵ�ǰ������ˢ�»��Ǽ���
	public static final int REFRESH = 0;
	public static final int LOAD = 1;

	// ����PULL��RELEASE�ľ���Ĵ�С
	private static final int SPACE = 20;

	private LayoutInflater inflater;
	private View footer;

	private TextView noData;
	private TextView loadFull;
	private TextView more;
	private ProgressBar loading;

	private RotateAnimation animation;
	private RotateAnimation reverseAnimation;

	private int startY;

	private int firstVisibleItem;
	private int scrollState;
	private int headerContentInitialHeight;
	private int headerContentHeight;

	// ֻ����listview��һ��item��ʾ��ʱ��listview�����˶������Ž�������ˢ�£� �����ʱ������ֻ�ǻ���listview
	private boolean isRecorded;
	private boolean isLoading;// �ж��Ƿ����ڼ���
	private boolean loadEnable = true;// �������߹رռ��ظ��๦��
	private boolean isLoadFull;
	private int pageSize = 10;

	private OnLoadListener onLoadListener;

	public AutoListViewLow(Context context) {
		super(context);
		initView(context);
	}

	public AutoListViewLow(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public AutoListViewLow(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}

	// ���ظ������
	public void setOnLoadListener(OnLoadListener onLoadListener) {
		this.loadEnable = true;
		this.onLoadListener = onLoadListener;
	}

	public boolean isLoadEnable() {
		return loadEnable;
	}

	// ����Ŀ������߹رռ��ظ��࣬����֧�ֶ�̬����
	public void setLoadEnable(boolean loadEnable) {
		this.loadEnable = loadEnable;
		this.removeFooterView(footer);
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	// ��ʼ�����
	private void initView(Context context) {

		// ���ü�ͷ��Ч
		animation = new RotateAnimation(0, -180,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		animation.setInterpolator(new LinearInterpolator());
		animation.setDuration(100);
		animation.setFillAfter(true);

		reverseAnimation = new RotateAnimation(-180, 0,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		reverseAnimation.setInterpolator(new LinearInterpolator());
		reverseAnimation.setDuration(100);
		reverseAnimation.setFillAfter(true);

		inflater = LayoutInflater.from(context);
		footer = inflater.inflate(R.layout.listview_footer, null);
		loadFull = (TextView) footer.findViewById(R.id.loadFull);
		noData = (TextView) footer.findViewById(R.id.noData);
		more = (TextView) footer.findViewById(R.id.more);
		loading = (ProgressBar) footer.findViewById(R.id.loading);

		this.addFooterView(footer);
		this.setOnScrollListener(this);
	}

	public void onLoad() {
		if (onLoadListener != null) {
			onLoadListener.onLoad();
		}
	}

	// ���ڼ��ظ��������Ļص�
	public void onLoadComplete() {
		isLoading = false;
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		this.firstVisibleItem = firstVisibleItem;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		this.scrollState = scrollState;
		ifNeedLoad(view, scrollState);
	}

	// ����listview������״̬�ж��Ƿ���Ҫ���ظ���
	private void ifNeedLoad(AbsListView view, int scrollState) {
		if (!loadEnable) {
			return;
		}
		try {
			if (scrollState == OnScrollListener.SCROLL_STATE_IDLE
					&& !isLoading
					&& view.getLastVisiblePosition() == view
							.getPositionForView(footer) && !isLoadFull) {
				onLoad();
				isLoading = true;
			}
		} catch (Exception e) {
		}
	}

	/**
	 * ��������Ǹ��ݽ���Ĵ�С������footer��ʾ�ġ�
	 * <p>
	 * ����ٶ�ÿ�����������Ϊ10�����������10��������Ϊ�������ݡ�����������10��������Ϊ�����Ѿ�ȫ�����أ���ʱfooter��ʾ�Ѿ�ȫ������
	 * </p>
	 * 
	 * @param resultSize
	 */
	public void setResultSize(int resultSize) {
		if (resultSize == 0) {
			isLoadFull = true;
			loadFull.setVisibility(View.GONE);
			loading.setVisibility(View.GONE);
			more.setVisibility(View.GONE);
			noData.setVisibility(View.VISIBLE);
		} else if (resultSize > 0 && resultSize < pageSize) {
			isLoadFull = true;
			loadFull.setVisibility(View.VISIBLE);
			loading.setVisibility(View.GONE);
			more.setVisibility(View.GONE);
			noData.setVisibility(View.GONE);
		} else if (resultSize == pageSize) {
			isLoadFull = false;
			loadFull.setVisibility(View.GONE);
			loading.setVisibility(View.VISIBLE);
			more.setVisibility(View.VISIBLE);
			noData.setVisibility(View.GONE);
		}
	}

	/*
	 * ������ظ���ӿ�
	 */
	public interface OnLoadListener {
		public void onLoad();
	}

}
