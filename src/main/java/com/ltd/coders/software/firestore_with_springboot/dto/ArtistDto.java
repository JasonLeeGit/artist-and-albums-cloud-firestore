package com.ltd.coders.software.firestore_with_springboot.dto;

import java.util.List;

import com.ltd.coders.software.firestore_with_springboot.dao.Artist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDto {
    private List<Artist> artists;
}
