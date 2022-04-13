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

import com.agrotis.api.event.RecursoCriadoEvent;
import com.agrotis.api.model.Laboratorio;
import com.agrotis.api.repository.LaboratorioRepository;
import com.agrotis.api.repository.filter.LaboratorioFilter;
import com.agrotis.api.service.LaboratorioService;

@RestController
@RequestMapping("/laboratorios")
public class LaboratorioResource {

	@Autowired
	private LaboratorioService laboratorioService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Laboratorio> listarTodos() {
		return laboratorioService.listarTodos();
	}

	@PostMapping
	public ResponseEntity<Laboratorio> criar(@Valid @RequestBody Laboratorio laboratorio,
			HttpServletResponse response) {
		Laboratorio laboratorioSalvo = laboratorioService.salvar(laboratorio);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, laboratorioSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(laboratorioSalvo);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Laboratorio> buscarPeloId(@PathVariable Long id) {
		Optional<Laboratorio> laboratorio = laboratorioService.buscarPorId(id);
		return laboratorio.isPresent() ? ResponseEntity.ok(laboratorio.get()) : ResponseEntity.notFound().build();
	}

	@GetMapping("/auto-complete")
	public List<Laboratorio> pesquisar(LaboratorioFilter laboratorioFilter) {
		List<Laboratorio> laboratorios = laboratorioService.buscarAutoComplete(laboratorioFilter);
		return laboratorios;
	}

}
