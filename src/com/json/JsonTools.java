package com.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.domain.ChatList;
import com.domain.HfInfoHead;
import com.domain.HfList;
import com.domain.TzInfo;
import com.domain.TzList;
import com.domain.User;

public class JsonTools {

	private static final String TAG = "JsonTools";

	public JsonTools() {
		// TODO Auto-generated constructor stub
	}

	public static User getUser(String json) {

		User user = new User();
		try {
			JSONObject userObject = new JSONObject(json);
			// JSONObject userObject = jsonObject.getJSONObject("islogin");
			user.setIslogin(userObject.getBoolean("islogin"));
			user.setName(userObject.getString("name"));
			user.setSid(userObject.getString("sid"));
			user.setUid(userObject.getString("uid"));
			user.setErrid(userObject.getInt("errid"));
			user.setErrmsg(userObject.getString("errmsg"));

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	public static HfInfoHead getHfInfoHead(String json) {
		HfInfoHead user = new HfInfoHead();
		try {
			JSONObject userObject = new JSONObject(json);
			// JSONObject userObject = jsonObject.getJSONObject("islogin");
			user.setTzid(userObject.getString("tzid"));
			user.setCount(userObject.getString("count"));
			user.setOffset(userObject.getInt("offset"));
			user.setSize(userObject.getInt("size"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	public static List<HfList> getHfLists(String json) {
		List<HfList> lists = new ArrayList<HfList>();
		try {
			JSONObject tzObject = new JSONObject(json);
			// tz.setSize(tzObject.getInt("size"));
			// tz.setCount(tzObject.getString("count"));
			JSONArray jsonArray = tzObject.getJSONArray("data");
			int iSize = jsonArray.length();
			for (int i = 0; i < iSize; i++) {
				HfList tz = new HfList();
				JSONObject jsonObj = jsonArray.getJSONObject(i);
				tz.setUid(jsonObj.getString("uid"));
				tz.setTzid(jsonObj.getString("tzid"));
				tz.setUname(jsonObj.getString("uname"));
				tz.setHftime(jsonObj.getString("hftime"));
				tz.setNr(jsonObj.getString("nr"));
				Log.i(TAG, tz.toString());
				lists.add(tz);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;
	}

	public static List<ChatList> getChatList(String json) {
		List<ChatList> lists = new ArrayList<ChatList>();

		try {
			JSONArray jsonArray = new JSONArray(json);
			// int iSize = jsonArray.length();
			for (int i = 0; i < 15; i++) {
				JSONObject chatObject = jsonArray.getJSONObject(i);

				ChatList cList = new ChatList();

				// JSONObject userObject = jsonObject.getJSONObject("islogin");
				cList.setName(chatObject.getString("name"));
				cList.setTime(chatObject.getString("timestr"));
				lists.add(cList);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;
	}

	public static List<TzList> getTz(String json) {
		List<TzList> lists = new ArrayList<TzList>();
		try {
			JSONObject tzObject = new JSONObject(json);
			// tz.setSize(tzObject.getInt("size"));
			// tz.setCount(tzObject.getString("count"));
			JSONArray jsonArray = tzObject.getJSONArray("data");
			int iSize = jsonArray.length();
			for (int i = 0; i < iSize; i++) {
				TzList tz = new TzList();
				JSONObject jsonObj = jsonArray.getJSONObject(i);
				tz.setBkid(jsonObj.getString("bkid"));
				tz.setId(jsonObj.getString("id"));
				tz.setTitle(jsonObj.getString("title"));
				tz.setUid(jsonObj.getString("uid"));
				tz.setUname(jsonObj.getString("uname"));
				tz.setFttime(jsonObj.getString("fttime"));
				tz.setHfcount(jsonObj.getString("hfcount"));
				tz.setHftime(jsonObj.getString("hftime"));
				tz.setRdcount(jsonObj.getString("rdcount"));
				lists.add(tz);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;
	}

	public static TzInfo getTzInfo(String json) {

		TzInfo info = new TzInfo();
		try {
			JSONObject userObject = new JSONObject(json);
			// JSONObject userObject = jsonObject.getJSONObject("islogin");
			info.setFttime(userObject.getString("fttime"));
			info.setHfcount(userObject.getString("hfcount"));
			info.setHftime(userObject.getString("hftime"));
			info.setUname(userObject.getString("uname"));
			info.setRdcount(userObject.getString("rdcount"));
			info.setNr(userObject.getString("nr"));
			info.setTzid(userObject.getString("tzid"));
			info.setBkid(userObject.getString("bkid"));
			info.setUid(userObject.getString("uid"));
			info.setTitle(userObject.getString("title"));

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}

}
