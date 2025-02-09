package com.ltd.coders.software.firestore_with_springboot.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.ltd.coders.software.firestore_with_springboot.dao.Artist;

public class ArtistUtils {

	public static List<Artist> getArtistList(Query query) throws InterruptedException, ExecutionException {
		ApiFuture<QuerySnapshot> querySnapshotApiFuture = query.get();
		List<QueryDocumentSnapshot> documents = querySnapshotApiFuture.get().getDocuments();
		List<Artist> artistList = new ArrayList<>();

		for (QueryDocumentSnapshot queryDocumentSnapshot : documents) {
			Artist artists = queryDocumentSnapshot.toObject(Artist.class);
			artistList.add(artists);
		}

		return artistList;
	}
}
