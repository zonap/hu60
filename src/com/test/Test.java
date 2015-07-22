package com.test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.domain.TzList;
import com.domain.User;
import com.http.NetworkTool;

public class Test {

	public Test() {
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

	private static void getTz(String json) {
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
				System.out.println(tz.toString());
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return null;
	}

	public static void main(String args[]) {
		String path = "http://133.130.53.62/wap/0wap/m.php/api.bbs.json?json=compact&type=tzlist&bkid=0&order=hftime&offset=0&size=20";
		String json = new NetworkTool().getContentFromUrl(path);
		System.out.println(json);
		getTz(json);
	}
}
