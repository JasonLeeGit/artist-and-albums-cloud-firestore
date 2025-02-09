package com.ltd.coders.software.firestore_with_springboot.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ltd.coders.software.firestore_with_springboot.dao.Artist;
import com.ltd.coders.software.firestore_with_springboot.dto.ArtistDto;
import com.ltd.coders.software.firestore_with_springboot.service.ArtistService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/artists")
@AllArgsConstructor
public class ArtistController {

	private final ArtistService artistService;

	@PostMapping()
	public ResponseEntity<String> createArtist(@RequestBody Artist artist) {
		return ResponseEntity.ok(artistService.createArtist(artist));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Artist> getArtist(@PathVariable String id) {
		return ResponseEntity.ok(artistService.getArtist(id));
	}

	@PutMapping()
	public ResponseEntity<String> updateUArtist(@RequestBody Artist artist) {
		return ResponseEntity.ok(artistService.updateArtist(artist));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteArtiat(@PathVariable("id") String id) {
		return ResponseEntity.ok(artistService.deleteArtist(id));
	}

	// http://localhost:8080/api/v1/artists/find-by-artist-names?names=Oasis,Blur,Coldplay
	@GetMapping("/find-by-artist-names")
	public ResponseEntity<ArtistDto> findByArtistNames(@RequestParam List<String> names) {
		names.stream().forEach(n -> System.out.println(n));
		return ResponseEntity.ok(artistService.findArtistByArtistNames(names));
	}

	// http://localhost:8080/api/v1/artists/fina-artist?name=Oasis
	@GetMapping("/find-artist")
	public ResponseEntity<ArtistDto> findArtistByName(@RequestParam String artistName) {
		return ResponseEntity.ok(artistService.findArtistByName(artistName));
	}
}
