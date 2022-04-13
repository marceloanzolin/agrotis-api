package com.agrotis.api.repository.laboratorio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.ObjectUtils;

import com.agrotis.api.model.Laboratorio;
import com.agrotis.api.repository.filter.LaboratorioFilter;

public class LaboratorioRepositoryImpl implements LaboratorioRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Laboratorio> filtrar(LaboratorioFilter laboratorioFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Laboratorio> criteria = builder.createQuery(Laboratorio.class);
		Root<Laboratorio> root = criteria.from(Laboratorio.class);

		Predicate[] predicates = criarRestricoes(laboratorioFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Laboratorio> query = manager.createQuery(criteria);
		return query.getResultList();

	}

	private Predicate[] criarRestricoes(LaboratorioFilter laboratorioFilter, CriteriaBuilder builder,
			Root<Laboratorio> root) {

		List<Predicate> predicates = new ArrayList<>();
		if (!ObjectUtils.isEmpty(laboratorioFilter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get("nome")),
					"%" + laboratorioFilter.getNome().toLowerCase() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
