package br.com.home.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.home.dtos.RecordDto;
import br.com.home.models.Record;
import br.com.home.services.RecordService;

@RestController
@RequestMapping("api/v1/records")
public class RecordController {

	@Autowired
	RecordService recordService;

	@PostMapping
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<URI> post(@RequestBody Record record) {

		RecordDto i = recordService.save(record);

		URI location = getUri(i.getId());

		return ResponseEntity.created(location).build();

	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

	@Secured({ "ROLE_ADMIN" })
	@PutMapping("/{id}")
	public ResponseEntity<RecordDto> update(@PathVariable Long id, @RequestBody Record record) {

		RecordDto recordDto = recordService.update(id, record);

		return ResponseEntity.ok(recordDto);
	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping
	public ResponseEntity<List<RecordDto>> getAll() {
		return ResponseEntity.ok(recordService.getAll());
	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping("/{id}")
	public ResponseEntity<RecordDto> getOne(@PathVariable("id") Long id) {
		Optional<RecordDto> record = recordService.getById(id);

		if (record.isPresent()) {
			return ResponseEntity.ok(record.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
