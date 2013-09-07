package com.example.instagram.lazylist;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.instagram.InstaImage;
import com.example.instagram.R;

public class InstaListAdapter extends BaseAdapter {

	private final LayoutInflater layoutInflater;
	public ImageLoader imageLoader;
	private ArrayList<InstaImage> images;

	public InstaListAdapter(Activity activity, ArrayList<InstaImage> images) {

		this.images = images;
		this.layoutInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		layoutInflater = (LayoutInflater) activity.getLayoutInflater();
////				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader(activity.getApplicationContext());
	}

	@Override
	public int getCount() {

		// Set the total list item count
		
		return images.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// Inflate the item layout and set the views
		View listItem = convertView;

		if (listItem == null) {
			listItem = layoutInflater.inflate(R.layout.list_item, null);
		}

		// Initialize the views in the layout
		ImageView iv = (ImageView) listItem.findViewById(R.id.thumb);
		TextView tvTitle = (TextView) listItem.findViewById(R.id.title);
		TextView tvDate = (TextView) listItem.findViewById(R.id.date);

		// Set the views in the layout
		imageLoader.DisplayImage(images.get(position).getImage(), iv);
		tvTitle.setText(images.get(position).getTitle());
		tvDate.setText(images.get(position).getUsername());

		return listItem;
	}
	
}
