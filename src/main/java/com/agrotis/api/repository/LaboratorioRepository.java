package com.agrotis.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agrotis.api.model.Laboratorio;
import com.agrotis.api.repository.laboratorio.LaboratorioRepositoryQuery;

@Repository
public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long>, LaboratorioRepositoryQuery {

}
