package com.ltd.coders.software.firestore_with_springboot.dao;

import java.util.List;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.firestore.annotation.ServerTimestamp;

import lombok.Data;

@Data

public class Artist {

	@DocumentId
	private String id;
	private String artistName;
	private String genre;
	private String format;
	private List<Album> albums;

	@ServerTimestamp
	private Timestamp createdAt;
}
