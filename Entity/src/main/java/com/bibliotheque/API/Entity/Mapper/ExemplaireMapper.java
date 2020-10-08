package com.bibliotheque.API.Entity.Mapper;

import com.bibliotheque.API.Entity.Exemplaire;
import com.bibliotheque.API.Entity.Dto.ExemplaireDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExemplaireMapper extends EntityMapper<ExemplaireDTO, Exemplaire> {
}
