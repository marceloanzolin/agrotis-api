package com.agrotis.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agrotis.api.model.Propriedade;

@Repository
public interface PropriedadeRepository extends JpaRepository<Propriedade, Long> {

}
