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

import br.com.home.dtos.IndicationDto;
import br.com.home.models.Indication;
import br.com.home.services.IndicationService;

@RestController
@RequestMapping("api/v1/indications")
public class IndicationController {

	@Autowired
	IndicationService indicationService;

	@PostMapping
	@Secured({ "ROLE_DOCTOR", "ROLE_ADMIN" })
	public ResponseEntity<URI> post(@RequestBody Indication indication) {

		IndicationDto i = indicationService.save(indication);

		URI location = getUri(i.getId());

		return ResponseEntity.created(location).build();

	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

	@Secured({ "ROLE_ADMIN" })
	@PutMapping("/{id}")
	public ResponseEntity<IndicationDto> update(@PathVariable Long id, @RequestBody Indication indication) {

		IndicationDto indicationDto = indicationService.update(id, indication);

		return ResponseEntity.ok(indicationDto);
	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping
	public ResponseEntity<List<IndicationDto>> getAll() {
		return ResponseEntity.ok(indicationService.getAll());
	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping("/{id}")
	public ResponseEntity<IndicationDto> getOne(@PathVariable("id") Long id) {
		Optional<IndicationDto> indication = indicationService.getById(id);

		if (indication.isPresent()) {
			return ResponseEntity.ok(indication.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<IndicationDto> delete(@PathVariable("id") Long id) {
		indicationService.delete(id);

		return ResponseEntity.ok().build();

	}
}