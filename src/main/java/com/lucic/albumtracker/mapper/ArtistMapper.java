package com.lucic.albumtracker.mapper;

import com.lucic.albumtracker.dto.ArtistDTO;
import com.lucic.albumtracker.entity.ArtistEntity;
import com.lucic.albumtracker.entity.AlbumEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ArtistMapper {

    @Mapping(target = "id", source = "artistEntity.id")
    @Mapping(target = "name", source = "artistEntity.name")
    ArtistDTO artistToArtistDTO(ArtistEntity artistEntity);

    @Mapping(target = "id", source = "artistDTO.id")
    @Mapping(target = "name", source = "artistDTO.name")
    ArtistEntity artistDTOToArtist(ArtistDTO artistDTO);
}
