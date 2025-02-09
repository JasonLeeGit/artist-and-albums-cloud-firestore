package com.ltd.coders.software.firestore_with_springboot.service;

import static com.ltd.coders.software.firestore_with_springboot.utils.ArtistUtils.getArtistList;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.WriteResult;
import com.ltd.coders.software.firestore_with_springboot.dao.Artist;
import com.ltd.coders.software.firestore_with_springboot.dto.ArtistDto;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ArtistService {

	private final Firestore firestore;

	public String createArtist(Artist artist) {
		try {
			ApiFuture<DocumentReference> artists = firestore.collection("artists").add(artist);
			// firestore.collection("users").document().set(user);
			return "Document saved: artistId is " + artists.get().getId();
		} catch (InterruptedException | ExecutionException e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public Artist getArtist(String id) {
		try {
			ApiFuture<DocumentSnapshot> artists = firestore.collection("artists").document(id).get();
			return artists.get().toObject(Artist.class);
		} catch (InterruptedException | ExecutionException e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public String updateArtist(Artist artist) {
		try {
			// to update multiple fields use a Map
			// Map<>String, Object> map = new HashMap<>();
			// map.put("username". users.getUserName());
			// map.put("emailId". users.getEmailId());
			// ApiFuture<WriteResult> users = firestore.collection("users")
			// .document(user.getId())
			// .update(map);

			ApiFuture<WriteResult> artists = firestore.collection("artists").document(artist.getId())
					// MUST get case correct IE artistName not artistname otherwise adds a new field
					// and does not update existing field!
					.update("artistName", artist.getArtistName());

			return "User updated at: " + artists.get().getUpdateTime();
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}
	}

	public String deleteArtist(String id) {
		try {
			ApiFuture<WriteResult> artist = firestore.collection("artists").document(id).delete();
			return "User deleted at : " + artist.get().getUpdateTime();
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}
	}

	public ArtistDto findArtistByName(String artistName) {
		if (artistName == null || artistName.isBlank()) {
			log.error("artistName is invalid");
			throw new RuntimeException("artistName is invalid!!");
		}

		try {
			// whereNotEqualTo
			Query query = firestore.collection("artists").whereEqualTo("artistName", artistName);
			List<Artist> artistList = getArtistList(query);
			return ArtistDto.builder().artists(artistList).build();
		} catch (InterruptedException | ExecutionException e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public ArtistDto findArtistByArtistNames(List<String> names) {
		try {
			Query query = firestore.collection("artists").whereIn("artistName", names);
			List<Artist> artistList = getArtistList(query);
			return ArtistDto.builder().artists(artistList).build();
		} catch (InterruptedException | ExecutionException e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
