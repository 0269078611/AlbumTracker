package com.lucic.albumtracker.mapper;
import com.lucic.albumtracker.dto.UserDTO;
import com.lucic.albumtracker.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(UserEntity userEntity);

    UserEntity userDTOToUser(UserDTO userDTO);

    List<UserDTO> userToUserDTOList(List<UserEntity> userEntities);

    List<UserEntity> userDTOToUserList(List<UserDTO> userDTOs);
}
