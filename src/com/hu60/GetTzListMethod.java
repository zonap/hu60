package com.hu60;

import java.util.List;

import com.domain.TzList;
import com.http.HttpTask;
import com.http.HttpTask.HttpTaskHandler;
import com.json.JsonTools;

public class GetTzListMethod {
	static List<TzList> tz;
	static List<TzList> getTzList(int offset) {
		HttpTask task = new HttpTask();
		task.setTaskHandler(new HttpTaskHandler() {
			public void taskSuccessful(String json) {
				try {
					tz = JsonTools.getTz(json);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			public void taskFailed() {
			}
		});
		task.execute("http://133.130.53.62/wap/0wap/m.php/api.bbs.json?json=compact&type=tzlist&bkid=0&order=hftime&offset="
				+ offset + "&size=15");
		return tz;
	}
}
