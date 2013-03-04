package com.ravis.softlock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SlLoginDAO {
	
	private SQLiteDatabase db;
	private DBHelper dbHelper;
	private String[] allColumns = { DBHelper.USER_NAME, DBHelper.USER_PASSWORD };
			
	public SlLoginDAO (Context context) {
		dbHelper = new DBHelper(context);
	}

	public void open() throws SQLException {
		db = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}
	
	public SlLogin  createSlLogin (byte[] password) {
		ContentValues values = new ContentValues();
		values.put(DBHelper.USER_NAME, "SYSADMIN");
		values.put(DBHelper.USER_PASSWORD, password);
		long insertId = db.insert(DBHelper.USER_CRED_TABLE_NAME, null, values);
		
		Cursor cursor = db.query(DBHelper.USER_CRED_TABLE_NAME, allColumns, "_id = " + insertId, null,null,null,null );
		cursor.moveToFirst();
		
		SlLogin newLogin = new SlLogin();
		newLogin.setUserName(cursor.getString(0));
		newLogin.setUserPassword(cursor.getBlob(1));
		
		cursor.close();
		
		return newLogin;
		
	}
	
	public int deleteSlCredentials( SlLogin slLogin) {
		return db.delete(DBHelper.USER_CRED_TABLE_NAME, DBHelper.USER_NAME + " = '" + slLogin.getUserName() + "'", null);
		
	}
	
	public SlLogin  getSlCredentials (String userName) {
		
		Cursor cursor = db.query(DBHelper.USER_CRED_TABLE_NAME, allColumns, DBHelper.USER_NAME + " = '" + userName + "'", null,null,null,null );
		
		Log.d(">>>DEBUG", ">>> CURSOR VALUE = " + cursor.getCount());
        if ( cursor.getCount() == 0 ) return null;
        
		cursor.moveToFirst();
		
		SlLogin newLogin = new SlLogin();
		
		newLogin.setUserName(cursor.getString(0));
		newLogin.setUserPassword(cursor.getBlob(1));
		
		cursor.close();
		
		return newLogin;
		
	}
	
}
