package com.ravis.softlock;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {

	private final static String DEFAULT_PASSWORD = "welcome";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

	public void doLogin(View view) {

		Log.d(">>>DEBUG", "Do login");
		EditText passwd = (EditText) findViewById(R.id.passwordText);

		if (LoginUtil.validateLogin(this,passwd.getText().toString())) {
			Intent softLockMenu = new Intent("com.ravis.softlock.STARTMENU");
			this.startActivity(softLockMenu);
		} else {
			// incorrect password... please re-enter.
			SoftLockUtil.message(this, "Incorrect password...Please re-enter");

			passwd.setText("");
		}
		
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}

}
