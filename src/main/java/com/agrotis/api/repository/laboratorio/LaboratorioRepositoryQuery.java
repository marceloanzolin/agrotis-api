package com.agrotis.api.repository.laboratorio;

import java.util.List;

import com.agrotis.api.model.Laboratorio;
import com.agrotis.api.repository.filter.LaboratorioFilter;

public interface LaboratorioRepositoryQuery {

	public List<Laboratorio> filtrar(LaboratorioFilter laboratorioFilter);
}
