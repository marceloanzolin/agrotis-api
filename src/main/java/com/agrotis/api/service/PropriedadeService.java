package com.agrotis.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.agrotis.api.model.Propriedade;
import com.agrotis.api.repository.PropriedadeRepository;

@Service
public class PropriedadeService {

	@Autowired
	private PropriedadeRepository propriedadeRepository;

	public List<Propriedade> listarTodos() {
		return propriedadeRepository.findAll();
	}

	public Propriedade salvar(Propriedade laboratorio) {
		return propriedadeRepository.save(laboratorio);
	}

	public Optional<Propriedade> buscarPorId(Long id) {
		return propriedadeRepository.findById(id);
	}

	public List<Propriedade> buscaAutoComplete(Propriedade filter) {
		Example<Propriedade> example = Example.of(filter, ExampleMatcher.matching().withIgnoreCase()
				.withIgnoreNullValues().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
		return propriedadeRepository.findAll(example);

	}

}
