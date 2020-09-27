package com.bibliotheque.Entity.Entity.Mapper;

import com.bibliotheque.Entity.Entity.Book;
import com.bibliotheque.Entity.Entity.Dto.BookDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper extends EntityMapper<BookDTO, Book>{


}
