package com.hu60;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends Activity {
	private final int SPLASH_DISPLAY_LENGHT = 3000; // —”≥Ÿ3√Î
	private SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		preferences = getSharedPreferences("phone", Context.MODE_PRIVATE);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				if (preferences.getBoolean("firststart", true)) {
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, Login.class);
					MainActivity.this.startActivity(intent);
					MainActivity.this.finish();
				} else {
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, Main.class);
					MainActivity.this.startActivity(intent);
					MainActivity.this.finish();
				}

			}
		}, SPLASH_DISPLAY_LENGHT);
	}

}
