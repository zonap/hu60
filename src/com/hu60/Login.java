package com.hu60;

import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.domain.User;
import com.http.HttpTask;
import com.http.HttpTask.HttpTaskHandler;
import com.json.JsonTools;

public class Login extends Activity implements OnClickListener {
	private EditText etName, etPw;
	private Button logiBtn;
	int n = 0;
	String s;

	public Login() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		etName = (EditText) findViewById(R.id.editText1);
		etPw = (EditText) findViewById(R.id.editText2);
		logiBtn = (Button) findViewById(R.id.button1);
		logiBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Login.this, Main.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}
		}
		// this
		);

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		logiBtn.setClickable(true);
	}

	@Override
	public void onClick(View v) {
		Toast.makeText(this, "kaishi", Toast.LENGTH_SHORT).show();
		logiBtn.setClickable(false);
		String name = null, pw = null;
		try {
			name = java.net.URLEncoder.encode(etName.getText().toString(),
					"utf-8");
			pw = java.net.URLEncoder.encode(etPw.getText().toString(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpTask task = new HttpTask();
		task.setTaskHandler(new HttpTaskHandler() {
			public void taskSuccessful(String json) {
				try {
					User user = JsonTools.getUser(json);
					if (user.getErrmsg().equals("µÇÂ½³É¹¦")) {
						Toast.makeText(Login.this, "³Â¹¬", Toast.LENGTH_SHORT)
								.show();
						Intent i = new Intent(Login.this, Main.class);
						Bundle mBundle = new Bundle();
						mBundle.putSerializable("account", user);
						i.putExtras(mBundle);
						startActivity(i);
					} else {
						Toast.makeText(Login.this, "Ê§°Ü", Toast.LENGTH_SHORT)
								.show();
						logiBtn.setClickable(true);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			public void taskFailed() {
			}
		});
		task.execute("http://133.130.53.62/wap/0wap/m.php/api.user.php?type=login&name="
				+ name + "&pass=" + pw);
	}

}
