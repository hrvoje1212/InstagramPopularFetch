package com.example.instagram;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class InstagramDataSource implements IDataSource {
	
	private static String CLIENT_ID = "3021e1b5cf52420ea491cbeead22ecb9";
	private static String LOG_TAG = "InstagramDataSource";
	private ArrayList<CustomImage> images;
	private MyCallback myCallback;
	
	public InstagramDataSource(){
	}

	@Override
	public void fetchImages() {

		AsyncHttpClient client = new AsyncHttpClient();
		
		client.get(
				"https://api.instagram.com/v1/media/popular?client_id=" + CLIENT_ID,
				new JsonHttpResponseHandler(){
					
					public void onSuccess(JSONObject object) {
						try {
							JSONArray data = object.getJSONArray("data");
							
							images = parseImages(data);
							
							myCallback.onDataFetched(images);
							
						} catch (JSONException e) {
							Log.e(LOG_TAG, "Failed parsing JSON Object");
							e.printStackTrace();
						}
					};
				}
		);
	}
	
	private ArrayList<CustomImage> parseImages(JSONArray data) throws JSONException{
		
		ArrayList<CustomImage> images = new ArrayList<CustomImage>();
		
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

			images.add(new CustomImage(image, username, title));
		}
		return images;
	}

	@Override
	public void setCallback(MyCallback callback) {
		myCallback = callback;
	}
}
