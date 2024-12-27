package com.luizalebs.comunicacao_api.business.mapper;


import com.luizalebs.comunicacao_api.api.dto.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.infraestructure.entities.ComunicacaoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComunicacaoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataHoraenvio", source = "dataHoraEnvio")
    ComunicacaoEntity paraComunicacaoEntity(ComunicacaoOutDTO dto);

    @Mapping(target = "dataHoraEnvio", source = "dataHoraenvio")
    ComunicacaoOutDTO paraComunicacaoDTO(ComunicacaoEntity entity);

}
