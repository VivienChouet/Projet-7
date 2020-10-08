package com.bibliotheque.API.Entity.Mapper;

import com.bibliotheque.API.Entity.Book;
import com.bibliotheque.API.Entity.Dto.BookDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper extends EntityMapper<BookDTO, Book>{


}
