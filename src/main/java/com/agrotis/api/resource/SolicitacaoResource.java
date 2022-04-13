package com.agrotis.api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.agrotis.api.event.RecursoCriadoEvent;
import com.agrotis.api.model.Solicitacao;
import com.agrotis.api.repository.SolicitacaoRepository;
import com.agrotis.api.service.SolicitacaoService;

@RestController
@RequestMapping("/solicitacoes")
public class SolicitacaoResource {

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private SolicitacaoService solicitacaoService;

	@GetMapping
	public List<Solicitacao> listarTodos() {
		return solicitacaoService.listarTodos();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Solicitacao> buscarPeloId(@PathVariable Long id) {
		Optional<Solicitacao> solicitacao = solicitacaoService.buscarPorId(id);
		return solicitacao.isPresent() ? ResponseEntity.ok(solicitacao.get()) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Solicitacao> criar(@Valid @RequestBody Solicitacao solicitacao,
			HttpServletResponse response) {
		Solicitacao solicitacaoSalva = solicitacaoService.salvar(solicitacao);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, solicitacaoSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(solicitacaoSalva);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Solicitacao> atualizar(@PathVariable Long id, @Valid @RequestBody Solicitacao solicitacao) {
		Solicitacao solicitacaoSalva = solicitacaoService.atualizar(id, solicitacao);
		return ResponseEntity.ok(solicitacaoSalva);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		solicitacaoService.excluir(id);

	}

	@GetMapping("/paginada")
	public Page<Solicitacao> listar(Pageable pageable) {
		Page<Solicitacao> solicitacoesPage = solicitacaoService.listarPaginado(pageable);
		List<Solicitacao> solicitacao = solicitacoesPage.getContent();
		Page<Solicitacao> solicitacaoPaginada = new PageImpl<>(solicitacao, pageable,
				solicitacoesPage.getTotalElements());
		return solicitacaoPaginada;
	}

}
