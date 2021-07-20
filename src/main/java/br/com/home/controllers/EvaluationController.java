package br.com.home.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.home.dtos.EvaluationDto;
import br.com.home.models.Evaluation;
import br.com.home.services.EvaluationService;

@RestController
@RequestMapping("api/v1/evaluations")
public class EvaluationController {

	@Autowired
	EvaluationService evaluationService;

	@PostMapping
	@Secured({ "ROLE_PHYSIOTHERAPIST", "ROLE_ADMIN" })
	public ResponseEntity<URI> post(@RequestBody Evaluation evaluation) {

		EvaluationDto i = evaluationService.save(evaluation);

		URI location = getUri(i.getId());

		return ResponseEntity.created(location).build();

	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

	@Secured({ "ROLE_PHYSIOTHERAPIST", "ROLE_ADMIN" })
	@PutMapping("/{id}")
	public ResponseEntity<EvaluationDto> update(@PathVariable Long id, @RequestBody Evaluation evaluation) {

		EvaluationDto evaluationDto = evaluationService.update(id, evaluation);

		return ResponseEntity.ok(evaluationDto);
	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping
	public ResponseEntity<List<EvaluationDto>> getAll() {
		return ResponseEntity.ok(evaluationService.getAll());
	}

	@Secured({ "ROLE_ADMIN", })
	@GetMapping("/{id}")
	public ResponseEntity<EvaluationDto> getOne(@PathVariable("id") Long id) {
		Optional<EvaluationDto> evaluation = evaluationService.getById(id);

		if (evaluation.isPresent()) {
			return ResponseEntity.ok(evaluation.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Secured({ "ROLE_PHYSIOTHERAPIST", "ROLE_ADMIN" })
	public ResponseEntity<EvaluationDto> delete(@PathVariable("id") Long id) {
		evaluationService.delete(id);

		return ResponseEntity.ok().build();

	}

}
