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

	private static String CLIENT_ID = "3021e1b5cf52420ea491cbeead22ecb9";
	private static String LOG_TAG = "MainActivity";
	private Context self;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		self = this;
		
		fetchImages();

	}
	
	public void fetchImages(){
		
		AsyncHttpClient client = new AsyncHttpClient();
		
		client.get(
				"https://api.instagram.com/v1/media/popular?client_id=" + CLIENT_ID,
				new JsonHttpResponseHandler(){
					
					public void onSuccess(JSONObject object) {
						try {
							JSONArray data = object.getJSONArray("data");
							
							ArrayList<InstaImage> images = parseImages(data);
							
							Intent i = new Intent(self, InstaListActivity.class);
							i.putExtra(InstaListActivity.ARRAY_LIST_IMAGES, images);
							startActivity(i);
							
						} catch (JSONException e) {
							Log.e(LOG_TAG, "Failed parsing JSON Object");
							e.printStackTrace();
						}
					};
				}
		);
	}
	
	private ArrayList<InstaImage> parseImages(JSONArray data) throws JSONException{
		
		ArrayList<InstaImage> images = new ArrayList<InstaImage>();
		
		int dataSize = data.length();
		
		for( int i = 0; i < dataSize; i++ ){
			JSONObject media = data.getJSONObject(i);
			
			String type = media.optString("type");
			
			if( !type.equals("image") )
				continue;
			
			String image = "";
			
			try{
				image = media.getJSONObject("images")
					.getJSONObject("standard_resolution")
					.getString("url");
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			String title = "";
			
			try{
				title = media.getJSONObject("caption")
					.getString("text");
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			String username = "";
			
			try{
				username = media.getJSONObject("user")
					.getString("username");
			}catch (Exception e) {
				e.printStackTrace();
			}

			images.add(new InstaImage(image, username, title));
		}
		return images;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
