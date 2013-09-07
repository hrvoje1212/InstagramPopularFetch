package com.example.instagram;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class MainActivity extends Activity {

	private static String LOG_TAG = "MainActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		DataSourceManager sourceManager = new DataSourceManager(this);
		
		IDataSource source = new InstagramDataSource();
		sourceManager.addSource(source);
		
		sourceManager.startFetchingData();
	}

}
