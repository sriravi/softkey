package com.ravis.softlock;

import java.util.TreeMap;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SoftLockMenu extends ListActivity {


	private TreeMap<String,String> slMenuMap = new TreeMap<String,String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		populateSLMap();
		String[] listArray = slMenuMap.keySet().toArray(new String[0]);
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listArray));
	}
	
	private void populateSLMap() {
	  slMenuMap.put("Create an entry", "com.ravis.softlock.SoftLockCreateActivity");
	  slMenuMap.put("Modify existing entry", "com.ravis.softlock.SoftLockUpdateActivity");
	  slMenuMap.put("Delete an entry", "com.ravis.softlock.SoftLockUpdateActivity");
	  slMenuMap.put("Search entry", "com.ravis.softlock.SoftLockUpdateActivity");
	  slMenuMap.put("Change password", "com.ravis.softlock.SoftLockPasswordChangeActivity");
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		super.onListItemClick(l, v, position, id);
		try {
			String selection = l.getItemAtPosition(position).toString();
			
			Log.d("DEBUG>>>" , selection  + "   .... " + position);
			
			Class slClass = Class.forName(slMenuMap.get(selection).toString());
			Intent slIntent = new Intent(this,slClass);
			startActivity(slIntent);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}


}
