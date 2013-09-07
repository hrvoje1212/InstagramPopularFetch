package com.example.instagram;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;

public class DataSourceManager implements MyCallback{

	private ArrayList<IDataSource> sources;
	private ArrayList<CustomImage> images;
	private int fetchedSources = 0;
	private Context context;
	
	public DataSourceManager(Context context) {
		this.context = context;
		sources = new ArrayList<IDataSource>();
		images = new ArrayList<CustomImage>();
	}
	
	public void addSource(IDataSource source){
		sources.add(source);
	}
	
	public void startFetchingData(){
		fetchedSources = 0;
		
		for (IDataSource source : sources) {
			source.setCallback(this);
			source.fetchImages();
		}
	}

	@Override
	public void onDataFetched(ArrayList<CustomImage> fetchedImages) {
		fetchedSources++;
		this.images.addAll(fetchedImages);
		System.out.println("images size" + images.size());
		if( fetchedSources == sources.size() )
			this.onFinishedFetching();
	}

	public void onFinishedFetching(){
		Intent i = new Intent(context, InstaListActivity.class);
		i.putExtra(InstaListActivity.ARRAY_LIST_IMAGES, images);
		context.startActivity(i);
	}
	
	
}
