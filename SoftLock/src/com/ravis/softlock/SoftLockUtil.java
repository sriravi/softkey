package com.ravis.softlock;

import android.app.AlertDialog;
import android.content.Context;

public class SoftLockUtil {

	public static void message(Context context, String msgStr) {
		AlertDialog alert = new AlertDialog.Builder(context).create();
		alert.setMessage(msgStr);
		alert.show();
	}
}
