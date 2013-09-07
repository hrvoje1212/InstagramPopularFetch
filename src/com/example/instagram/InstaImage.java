package com.example.instagram;

import java.io.Serializable;

public class InstaImage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String image;
	private String username;
	private String title;
	
	public InstaImage() {
		image = "";
		username = "";
		title = "";
	}
	
	public InstaImage(String image, String username, String title){
		this.image = image;
		this.username = username;
		this.title = title;
	}
	
	public String getImage() {
		return image;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getTitle() {
		return title;
	}
	
	@Override
	public String toString() {
		String s = "";
		s += "----------------------------------------\n";
		s += username + "\n";
		s += title + "\n";
		s += image + "\n";
		return s;
	}
}
