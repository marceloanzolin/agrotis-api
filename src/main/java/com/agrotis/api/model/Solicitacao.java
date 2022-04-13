package com.agrotis.api.model;


import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="solicitacao")
public class Solicitacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 3,max = 100)
	private String nome;
	
	@NotNull
	private LocalDateTime dataInicial;
	
	@NotNull
	private LocalDateTime dataFinal;
	
	@NotNull
	@ManyToMany
	@JoinTable(name="solicitacao_propriedade",
		joinColumns = @JoinColumn (name = "solicitacao_id"),
		inverseJoinColumns = @JoinColumn (name = "propriedade_id"))
	private List<Propriedade> infosPropriedade;

	@NotNull
	@Size(min = 18)
	private String cnpj;
	
	@NotNull
	@ManyToOne
    @JoinColumn(name = "id_laboratorio")
	private Laboratorio laboratorio;
	

	@JsonAlias("observacoes")
	private String observacao;

}
