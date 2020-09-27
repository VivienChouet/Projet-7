package com.bibliotheque.Entity.Entity.Mapper;

import com.bibliotheque.Entity.Entity.Dto.ExemplaireDTO;
import com.bibliotheque.Entity.Entity.Exemplaire;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExemplaireMapper extends EntityMapper<ExemplaireDTO, Exemplaire> {
}
