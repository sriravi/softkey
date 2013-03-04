package com.ravis.softlock;

import android.content.Context;
import android.util.Log;

public class LoginUtil{

	private static StringEncrypter crypter = new StringEncrypter("secretstring");

	public static boolean updateLogin (Context context, String password) {
		SlLoginDAO ds = new SlLoginDAO(context);
		ds.open();
		SlLogin cred = ds.getSlCredentials("SYSADMIN");
		ds.deleteSlCredentials(cred);
		cred = ds.createSlLogin(crypter.encrypt(password));
		
		if ( cred != null ) return true; else return false;
		
	}
	
	public static boolean validateLogin(Context context, String password ) {
		SlLoginDAO ds = new SlLoginDAO(context);
		ds.open();
		SlLogin cred = ds.getSlCredentials("SYSADMIN");
		
		if ( cred == null) { // first time
			
		    Log.d(">>>DEBUG","USER CREDENTIAL IS NULL....CREATING A DEFAULT USER CREDENTIAL ");
			cred = ds.createSlLogin(crypter.encrypt("welcome"));
			
		} 
		
		Log.d(">>>DEBUG", "Encrypted Password :" +  cred.getUserPassword().toString());
		String decryptedPasswd = crypter.decrypt(cred.getUserPassword());
		ds.close();
		
		if (password != null && password.equals(decryptedPasswd)) {
					
			return true;
			
		} else {
			
			return false;
		}
		

	}
}
