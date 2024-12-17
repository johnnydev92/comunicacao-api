package com.luizalebs.comunicacao_api.infraestructure.repositories;

import com.luizalebs.comunicacao_api.infraestructure.entities.ComunicacaoEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

public interface ComunicacaoRepository extends CrudRepository<ComunicacaoEntity, Long> {

    ComunicacaoEntity findByEmailDestinatario(String nomeDestinatario);

    @Transactional
    void deleByEmail(String email);
}
