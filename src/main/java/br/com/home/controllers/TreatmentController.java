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

import br.com.home.dtos.TreatmentDto;
import br.com.home.models.Treatment;
import br.com.home.services.TreatmentService;

@RestController
@RequestMapping("api/v1/treatments")
public class TreatmentController {

	@Autowired
	TreatmentService treatmentService;

	@PostMapping
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<URI> post(@RequestBody Treatment treatment) {

		TreatmentDto i = treatmentService.save(treatment);

		URI location = getUri(i.getId());

		return ResponseEntity.created(location).build();

	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

	@Secured({ "ROLE_ADMIN" })
	@PutMapping("/{id}")
	public ResponseEntity<TreatmentDto> update(@PathVariable Long id, @RequestBody Treatment treatment) {

		TreatmentDto treatmentDto = treatmentService.update(id, treatment);

		return ResponseEntity.ok(treatmentDto);
	}

	@GetMapping
	public ResponseEntity<List<TreatmentDto>> getAll() {
		return ResponseEntity.ok(treatmentService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<TreatmentDto> getOne(@PathVariable("id") Long id) {
		Optional<TreatmentDto> treatment = treatmentService.getById(id);

		if (treatment.isPresent()) {
			return ResponseEntity.ok(treatment.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<TreatmentDto> delete(@PathVariable("id") Long id) {
		treatmentService.delete(id);

		return ResponseEntity.ok().build();

	}

}
