package com.ravis.softlock;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper{


	
	private static int DATABASE_VERSION = 7;
	private static final String DATABASE_NAME = "SOFT_LOCK_DB";
	public static final String USER_NAME = "user_name";
	public static final String USER_PASSWORD = "user_password";
	public static final String USER_CRED_TABLE_NAME = "SL_LOGIN";
	
	private static final String CREATE_SL_LOGIN = " create table SL_LOGIN ( _id integer primary key autoincrement, " +
	" user_name text not null, user_password b not null)";

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("CREATING DB:" , CREATE_SL_LOGIN);
		db.execSQL(CREATE_SL_LOGIN);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 Log.d("UPDATING DB:",
			        "Upgrading database from version " + oldVersion + " to "
			            + newVersion + ", which will destroy all old data");
			    db.execSQL("DROP TABLE IF EXISTS SL_LOGIN"  );
			    onCreate(db);	
	}

	@Override
	public synchronized void close() {
		super.close();
	}

	
	
}
