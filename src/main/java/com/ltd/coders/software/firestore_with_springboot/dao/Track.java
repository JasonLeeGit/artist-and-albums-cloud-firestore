package com.ltd.coders.software.firestore_with_springboot.dao;

import lombok.Data;

@Data
public class Track {

	private String albumName;
	private String bitRate;
	private String genre;
	private String title;
	private String fileFormat;
	private String recordLabel;
	private String releaseYear;
	private String trackLength;
	private String trackNumber;
	private boolean isVariableBitRate;
}
