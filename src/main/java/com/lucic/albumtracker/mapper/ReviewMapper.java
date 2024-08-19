package com.lucic.albumtracker.mapper;

import com.lucic.albumtracker.dto.ReviewDTO;
import com.lucic.albumtracker.entity.ReviewEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewDTO toDto(ReviewEntity reviewEntity);
    ReviewEntity toEntity(ReviewDTO reviewDTO);
}
