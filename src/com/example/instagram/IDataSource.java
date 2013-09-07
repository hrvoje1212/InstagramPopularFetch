package com.example.instagram;


public interface IDataSource {

	public void fetchImages();
	public void setCallback(MyCallback callback);
}
