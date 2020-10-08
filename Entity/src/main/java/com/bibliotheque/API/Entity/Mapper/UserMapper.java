package com.bibliotheque.API.Entity.Mapper;

import com.bibliotheque.API.Entity.User;
import com.bibliotheque.API.Entity.Dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User>{
}
