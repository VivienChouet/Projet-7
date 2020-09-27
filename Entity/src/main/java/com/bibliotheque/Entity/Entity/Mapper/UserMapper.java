package com.bibliotheque.Entity.Entity.Mapper;

import com.bibliotheque.Entity.Entity.Dto.UserDTO;
import com.bibliotheque.Entity.Entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User>{
}
