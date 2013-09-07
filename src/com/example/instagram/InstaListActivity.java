package com.example.instagram;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.instagram.lazylist.InstaListAdapter;

public class InstaListActivity extends ListActivity {

	public static String ARRAY_LIST_IMAGES = "array_list_images";
	
	private ArrayList<CustomImage> images;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insta_list);
		
		images = (ArrayList<CustomImage>) getIntent().getSerializableExtra(ARRAY_LIST_IMAGES);
		
		for (CustomImage instaImage : images) {
			System.out.println(instaImage);
		}
		
		setListAdapter(new InstaListAdapter(this, images));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		Intent i = new Intent();
	}

}
