package com.agrotis.api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agrotis.api.dto.PropriedadeDTO;
import com.agrotis.api.event.RecursoCriadoEvent;
import com.agrotis.api.model.Laboratorio;
import com.agrotis.api.model.Propriedade;
import com.agrotis.api.repository.PropriedadeRepository;
import com.agrotis.api.repository.filter.LaboratorioFilter;
import com.agrotis.api.service.PropriedadeService;

@RestController
@RequestMapping("/propriedades")
public class PropriedadeResource {

	@Autowired
	private PropriedadeService propriedadeService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Propriedade> listarTodos() {
		return propriedadeService.listarTodos();
	}

	@PostMapping
	public ResponseEntity<Propriedade> criar(@Valid @RequestBody Propriedade propriedade,
			HttpServletResponse response) {
		Propriedade propriedadeSalva = propriedadeService.salvar(propriedade);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, propriedadeSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(propriedadeSalva);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Propriedade> buscarPorId(@PathVariable Long id) {
		Optional<Propriedade> propriedade = propriedadeService.buscarPorId(id);
		return propriedade.isPresent() ? ResponseEntity.ok(propriedade.get()) : ResponseEntity.notFound().build();
	}

	@GetMapping("/auto-complete")
	public List<Propriedade> pesquisar(Propriedade propriedade) {
		List<Propriedade> propriedades = propriedadeService.buscaAutoComplete(propriedade);
		return propriedades;
	}

}
