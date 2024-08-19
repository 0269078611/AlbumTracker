package com.lucic.albumtracker.mapper;

import com.lucic.albumtracker.dto.AlbumDTO;
import com.lucic.albumtracker.dto.ArtistDTO;
import com.lucic.albumtracker.entity.ArtistEntity;
import com.lucic.albumtracker.entity.AlbumEntity;
import com.lucic.albumtracker.entity.GenreEntity;
import com.lucic.albumtracker.entity.SongEntity;
import com.lucic.albumtracker.exception.NotFoundException;

import com.lucic.albumtracker.repository.ArtistRepository;
import com.lucic.albumtracker.repository.GenreRepository;
import com.lucic.albumtracker.repository.SongRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {ArtistMapper.class, SongMapper.class, ReviewMapper.class, GenreMapper.class})
public interface AlbumMapper {

    @Mapping(target = "artistIds", source = "artists", qualifiedByName = "albumMapArtistsToIds")
    @Mapping(target = "songIds", source = "songs", qualifiedByName = "albumMapSongsToIds")
    @Mapping(target = "genreId", source = "genre.id")
    AlbumDTO toDto(AlbumEntity albumEntity);

    @Mapping(target = "artists", source = "artistIds", qualifiedByName = "albumMapIdsToArtists")
    @Mapping(target = "songs", source = "songIds", qualifiedByName = "albumMapIdsToSongs")
    @Mapping(target = "genre", source = "genreId", qualifiedByName = "mapIdToGenre")
    AlbumEntity toEntity(AlbumDTO albumDTO);

    @Named("albumMapArtistsToIds")
    default Set<UUID> albumMapArtistsToIds(Set<ArtistEntity> artists) {
        if (artists == null || artists.isEmpty()) {
            return Collections.emptySet();
        }
        return artists.stream()
                .map(ArtistEntity::getId)
                .collect(Collectors.toSet());
    }

    @Named("albumMapSongsToIds")
    default Set<UUID> albumMapSongsToIds(Set<SongEntity> songs) {
        if (songs == null || songs.isEmpty()) {
            return Collections.emptySet();
        }
        return songs.stream()
                .map(SongEntity::getId)
                .collect(Collectors.toSet());
    }

    @Named("albumMapIdsToArtists")
    default Set<ArtistEntity> albumMapIdsToArtists(Set<UUID> artistIds, @Context ArtistRepository artistRepository) {
        if (artistIds == null || artistIds.isEmpty()) {
            return Collections.emptySet();
        }
        return artistIds.stream()
                .map(id -> artistRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Artist not found.")))
                .collect(Collectors.toSet());
    }

    @Named("albumMapIdsToSongs")
    default Set<SongEntity> albumMapIdsToSongs(Set<UUID> songIds, @Context SongRepository songRepository) {
        if (songIds == null || songIds.isEmpty()) {
            return Collections.emptySet();
        }
        return songIds.stream()
                .map(id -> songRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Song not found")))
                .collect(Collectors.toSet());
    }

    @Named("mapIdToGenre")
    default GenreEntity mapIdToGenre(UUID genreId, @Context GenreRepository genreRepository) {
        return genreRepository.findById(genreId)
                .orElseThrow(() -> new NotFoundException("Genre not found"));
    }
}
