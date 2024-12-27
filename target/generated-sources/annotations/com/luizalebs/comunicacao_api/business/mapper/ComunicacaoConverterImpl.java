package com.luizalebs.comunicacao_api.business.mapper;

import com.luizalebs.comunicacao_api.api.dto.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.infraestructure.entities.ComunicacaoEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-18T16:49:38-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class ComunicacaoConverterImpl implements ComunicacaoConverter {

    @Override
    public ComunicacaoEntity paraComunicacaoEntity(ComunicacaoOutDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ComunicacaoEntity.ComunicacaoEntityBuilder comunicacaoEntity = ComunicacaoEntity.builder();

        comunicacaoEntity.dataHoraenvio( dto.getDataHoraEnvio() );
        comunicacaoEntity.nomeDestinatario( dto.getNomeDestinatario() );
        comunicacaoEntity.emailDestinatario( dto.getEmailDestinatario() );
        comunicacaoEntity.telefoneDestinatario( dto.getTelefoneDestinatario() );
        comunicacaoEntity.mensagem( dto.getMensagem() );
        comunicacaoEntity.modoDeEnvio( dto.getModoDeEnvio() );
        comunicacaoEntity.statusEnvio( dto.getStatusEnvio() );

        return comunicacaoEntity.build();
    }

    @Override
    public ComunicacaoOutDTO paraComunicacaoDTO(ComunicacaoEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ComunicacaoOutDTO.ComunicacaoOutDTOBuilder comunicacaoOutDTO = ComunicacaoOutDTO.builder();

        comunicacaoOutDTO.dataHoraEnvio( entity.getDataHoraenvio() );
        comunicacaoOutDTO.nomeDestinatario( entity.getNomeDestinatario() );
        comunicacaoOutDTO.emailDestinatario( entity.getEmailDestinatario() );
        comunicacaoOutDTO.telefoneDestinatario( entity.getTelefoneDestinatario() );
        comunicacaoOutDTO.mensagem( entity.getMensagem() );
        comunicacaoOutDTO.modoDeEnvio( entity.getModoDeEnvio() );
        comunicacaoOutDTO.statusEnvio( entity.getStatusEnvio() );

        return comunicacaoOutDTO.build();
    }
}
