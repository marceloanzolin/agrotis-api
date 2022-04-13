package com.agrotis.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agrotis.api.model.Laboratorio;
import com.agrotis.api.repository.LaboratorioRepository;
import com.agrotis.api.repository.filter.LaboratorioFilter;

@Service
public class LaboratorioService {

	@Autowired
	private LaboratorioRepository laboratorioRepository;
	
	
	public List<Laboratorio> listarTodos(){
		return laboratorioRepository.findAll();
	}
	
	public Laboratorio salvar(Laboratorio laboratorio){
		return laboratorioRepository.save(laboratorio);
	}
	
	public  Optional<Laboratorio> buscarPorId(Long id){
		return laboratorioRepository.findById(id);
	}
	
	public List<Laboratorio> buscarAutoComplete(LaboratorioFilter laboratorioFilter){
		return laboratorioRepository.filtrar(laboratorioFilter);
	}
	
}
