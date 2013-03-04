package com.ravis.softlock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class SoftLockPasswordChangeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_soft_lock_password_change);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_soft_lock_password_change,
				menu);
		return true;
	}
	
	public void doCancel(View view) {
		Log.d(">>>DEBUG",">>CANCEL");
		Intent softLockMenu = new Intent("com.ravis.softlock.STARTMENU");
		this.startActivity(softLockMenu);
	}
	
	public void doChangePassword(View view) {
		Log.d(">>>DEBUG",">>CHG PASSWORD");
		EditText oldPassword = (EditText) findViewById(R.id.oldPasswordText);
		
		if (! LoginUtil.validateLogin(this,oldPassword.getText().toString())) {
			SoftLockUtil.message(this, "Incorrect old password...Please re-enter");
			oldPassword.setText("");
			return;
		}
		
		EditText newPassword = (EditText) findViewById(R.id.newPasswordText);
		EditText reNewPassword = (EditText) findViewById(R.id.rNewPasswordText);
		
		if ( ! newPassword.getText().toString().equals(reNewPassword.getText().toString())) {
			SoftLockUtil.message(this, "New Passwords does not match...Please re-enter");
			newPassword.setText("");
			reNewPassword.setText("");
			return;
		}
		
		if ( LoginUtil.updateLogin(this, newPassword.getText().toString()) ) {
			SoftLockUtil.message(this, "Password updated Successfully");
			Intent softLockMenu = new Intent("com.ravis.softlock.STARTMENU");
			this.startActivity(softLockMenu);
		} else {
			SoftLockUtil.message(this, "Unable to update the password.");
		}
		

	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}

}
