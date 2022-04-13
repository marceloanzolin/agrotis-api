package com.agrotis.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.agrotis.api.model.Solicitacao;
import com.agrotis.api.repository.SolicitacaoRepository;

@Service
public class SolicitacaoService {

	@Autowired
	private SolicitacaoRepository solicitacaoRepository;

	public List<Solicitacao> listarTodos() {
		return solicitacaoRepository.findAll();
	}

	public Solicitacao salvar(Solicitacao solicitacao) {
		return solicitacaoRepository.save(solicitacao);
	}

	public Optional<Solicitacao> buscarPorId(Long id) {
		return solicitacaoRepository.findById(id);
	}

	public void excluir(Long id) {
		solicitacaoRepository.deleteById(id);
	}

	public Page<Solicitacao> listarPaginado(Pageable pageable) {
		return solicitacaoRepository.findAll(pageable);
	}

	public Solicitacao atualizar(Long id, Solicitacao solicitacao) {

		Solicitacao solicitacaoSalva = solicitacaoRepository.findById(id)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));

		BeanUtils.copyProperties(solicitacao, solicitacaoSalva, "id");

		return this.solicitacaoRepository.save(solicitacaoSalva);
	}

}
