package com.lucic.albumtracker.mapper;
import com.lucic.albumtracker.dto.SongDTO;
import com.lucic.albumtracker.entity.ArtistEntity;
import com.lucic.albumtracker.entity.AlbumEntity;
import com.lucic.albumtracker.entity.SongEntity;
import com.lucic.albumtracker.exception.NotFoundException;

import com.lucic.albumtracker.repository.ArtistRepository;
import com.lucic.albumtracker.repository.AlbumRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
@Mapper(componentModel = "spring", uses = {AlbumMapper.class, ArtistMapper.class})
public interface SongMapper {

    @Mapping(target = "albumId", source = "album.id")
    @Mapping(target = "artistIds", source = "artists")
    SongDTO toDto(SongEntity songEntity);

    @Mapping(target = "album", source = "albumId")
    @Mapping(target = "artists", source = "artistIds")
    SongEntity toEntity(SongDTO songDTO);


}
