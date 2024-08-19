//package com.lucic.albumtracker.mapper;
//import com.lucic.albumtracker.dto.UserDTO;
//import com.lucic.albumtracker.entity.UserEntity;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//
//import java.util.List;
//
//@Mapper(componentModel = "spring")
//public interface UserMapper {
//
//
//
//    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
//
//    @Mapping(target = "id", source = "userDTO.id")
//    @Mapping(target = "username", source = "userDTO.username")
//    @Mapping(target = "email", source = "userDTO.email")
//    @Mapping(target = "password", source = "userDTO.password")
//    @Mapping(target = "role", source = "userDTO.role")
//    UserEntity userDTOToUser(UserDTO userDTO);
//
//    @Mapping(target = "id", source = "userEntity.id")
//    @Mapping(target = "username", source = "userEntity.username")
//    @Mapping(target = "email", source = "userEntity.email")
//    @Mapping(target = "password", source = "userEntity.password")
//    @Mapping(target = "role", source = "userEntity.role")
//    UserDTO userToUserDTO(UserEntity userEntity);
//}
