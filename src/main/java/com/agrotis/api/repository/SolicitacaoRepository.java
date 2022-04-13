package com.agrotis.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agrotis.api.model.Solicitacao;
import com.agrotis.api.repository.solicitacao.SolicitacaoRepositoryQuery;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long>, SolicitacaoRepositoryQuery {

}