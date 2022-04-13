package com.agrotis.api.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.agrotis.api.model.Propriedade;
import com.agrotis.api.service.PropriedadeService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CadastroPropriedadeIntegracaoTests {

	@Autowired
	private PropriedadeService propriedadeService;
	
	@Test
	public void testarPropriedadeComSucesso() {
		// cenário
		Propriedade novaPropriedade = new Propriedade();
		novaPropriedade.setNome("Nova Propriedade");
		
		// ação
		novaPropriedade = propriedadeService.salvar(novaPropriedade);
		
		// validação
		assertThat(novaPropriedade).isNotNull();
		assertThat(novaPropriedade.getId()).isNotNull();
	}
	
	@Test
	public void testarCadastroPropriedadeSemNome() {
	   Propriedade novaPropriedade = new Propriedade();
	   novaPropriedade.setNome(null);
	   
	   javax.validation.ConstraintViolationException erroEsperado =
			      Assertions.assertThrows(javax.validation.ConstraintViolationException.class, () -> {
			    	  propriedadeService.salvar(novaPropriedade);
			      });
	   
	   assertThat(erroEsperado).isNotNull();
	}
}
