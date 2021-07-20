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

import br.com.home.dtos.SectionDto;
import br.com.home.models.Section;
import br.com.home.services.SectionService;

@RestController
@RequestMapping("api/v1/sections")
public class SectionController {

	@Autowired
	SectionService sectionService;

	@PostMapping
	@Secured({ "ROLE_PHYSIOTHERAPIST", "ROLE_ADMIN" })
	public ResponseEntity<URI> post(@RequestBody Section section) {

		SectionDto i = sectionService.save(section);

		URI location = getUri(i.getId());

		return ResponseEntity.created(location).build();

	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

	@Secured({ "ROLE_ADMIN", "ROLE_PHYSIOTHERAPIST" })
	@PutMapping("/{id}")
	public ResponseEntity<SectionDto> update(@PathVariable Long id, @RequestBody Section section) {

		SectionDto sectionDto = sectionService.update(id, section);

		return ResponseEntity.ok(sectionDto);
	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping
	public ResponseEntity<List<SectionDto>> getAll() {
		return ResponseEntity.ok(sectionService.getAll());
	}

	@Secured({ "ROLE_ADMIN", "ROLE_PHYSIOTHERAPIST" })
	@GetMapping("/physiotherapist/{id}")
	public ResponseEntity<List<SectionDto>> getByPhysiotherapist(@PathVariable Long id) {
		return ResponseEntity.ok(sectionService.getByPhysiotherapist(id));
	}

	@Secured({ "ROLE_ADMIN", "ROLE_CLIENT" })
	@GetMapping("/client/{id}")
	public ResponseEntity<List<SectionDto>> getByClient(@PathVariable Long id) {
		return ResponseEntity.ok(sectionService.getByClient(id));
	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping("/{id}")
	public ResponseEntity<SectionDto> getOne(@PathVariable("id") Long id) {
		Optional<SectionDto> section = sectionService.getById(id);

		if (section.isPresent()) {
			return ResponseEntity.ok(section.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Secured({ "ROLE_ADMIN", "ROLE_PHYSIOTHERAPIST" })
	public ResponseEntity<SectionDto> delete(@PathVariable("id") Long id) {
		sectionService.delete(id);

		return ResponseEntity.ok().build();

	}

}
