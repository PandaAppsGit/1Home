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

import br.com.home.dtos.BillDto;
import br.com.home.models.Bill;
import br.com.home.services.BillService;

@RestController
@RequestMapping("api/v1/bills")
public class BillController {

	@Autowired
	BillService billService;

	@PostMapping
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<URI> post(@RequestBody Bill bill) {

		BillDto i = billService.save(bill);

		URI location = getUri(i.getId());

		return ResponseEntity.created(location).build();

	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

	@Secured({ "ROLE_ADMIN" })
	@PutMapping("/{id}")
	public ResponseEntity<BillDto> update(@PathVariable Long id, @RequestBody Bill bill) {

		BillDto billDto = billService.update(id, bill);

		return ResponseEntity.ok(billDto);
	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping
	public ResponseEntity<List<BillDto>> getAll() {
		return ResponseEntity.ok(billService.getAll());
	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping("/{id}")
	public ResponseEntity<BillDto> getOne(@PathVariable("id") Long id) {
		Optional<BillDto> bill = billService.getById(id);

		if (bill.isPresent()) {
			return ResponseEntity.ok(bill.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<BillDto> delete(@PathVariable("id") Long id) {
		billService.delete(id);

		return ResponseEntity.ok().build();

	}

}
