//package com.lucic.albumtracker.mapper;
//
//import com.lucic.albumtracker.dto.ArtistDTO;
//import com.lucic.albumtracker.entity.ArtistEntity;
//import com.lucic.albumtracker.entity.AlbumEntity;
//import com.lucic.albumtracker.entity.SongEntity;
//import com.lucic.albumtracker.exception.NotFoundException;
//import com.lucic.albumtracker.repository.AlbumRepository;
//import com.lucic.albumtracker.repository.SongRepository;
//import org.mapstruct.Context;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Named;
//
//import java.util.Collections;
//import java.util.Set;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//
//@Mapper(componentModel = "spring", uses = {AlbumMapper.class, SongMapper.class})
//public interface ArtistMapper {
//
//
//    @Mapping(target = "albumIds", source = "albums")
//    @Mapping(target = "songIds", source = "songs", qualifiedByName = "artistMapSongsToIds")
//    ArtistDTO toDto(ArtistEntity artistEntity);
//
//    @Mapping(target = "albums", source = "albumIds", qualifiedByName = "artistMapIdsToAlbums")
//    @Mapping(target = "songs", source = "songIds", qualifiedByName = "artistMapIdsToSongs")
//    ArtistEntity toEntity(ArtistDTO artistDTO);
//
//
//    @Named("artistMapSongsToIds")
//    default Set<UUID> artistMapSongsToIds(Set<SongEntity> songs) {
//        if (songs == null || songs.isEmpty()) {
//            return Collections.emptySet();
//        }
//        return songs.stream()
//                .map(SongEntity::getId)
//                .collect(Collectors.toSet());
//    }
//
//    @Named("artistMapIdsToAlbums")
//    default Set<AlbumEntity> artistMapIdsToAlbums(Set<UUID> albumIds, @Context AlbumRepository albumRepository) {
//        if (albumIds == null || albumIds.isEmpty()) {
//            return Collections.emptySet();
//        }
//        return albumIds.stream()
//                .map(id -> albumRepository.findById(id)
//                        .orElseThrow(() -> new NotFoundException("Album not found.")))
//                .collect(Collectors.toSet());
//    }
//    @Named("artistMapIdsToSongs")
//    default Set<SongEntity> artistMapIdsToSongs(Set<UUID> songIds, @Context SongRepository songRepository) {
//        if (songIds == null || songIds.isEmpty()) {
//            return Collections.emptySet();
//        }
//        return songIds.stream()
//                .map(id -> songRepository.findById(id)
//                        .orElseThrow(() -> new NotFoundException("Song not found.")))
//                .collect(Collectors.toSet());
//    }
//}
