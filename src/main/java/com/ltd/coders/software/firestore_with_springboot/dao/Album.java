package com.ltd.coders.software.firestore_with_springboot.dao;

import java.util.List;

import lombok.Data;

@Data
public class Album {
	
	private String artistName;
	private String albumName;
	private List<Track> tracks;
}
