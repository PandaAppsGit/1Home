package br.com.home.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

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

import br.com.home.dtos.DadUserDto;
import br.com.home.dtos.PhysiotherapistDto;
import br.com.home.models.Physiotherapist;
import br.com.home.services.DadUserService;
import br.com.home.services.PhysiotherapistService;

@RestController
@RequestMapping("api/v1/physiotherapists")
public class PhysiotherapistController {

	@Autowired
	DadUserService dadUserService;

	@Autowired
	PhysiotherapistService physiotherapistService;

	@PostMapping("/sign-up")
	public ResponseEntity<URI> signUp(@RequestBody Physiotherapist physiotherapist, HttpServletRequest request) {

		DadUserDto dadUser = dadUserService.signUp(physiotherapist, "ROLE_DOCTOR", request);

		URI location = getUri(dadUser.getId());

		return ResponseEntity.created(location).build();

	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping
	public ResponseEntity<List<PhysiotherapistDto>> getAll() {
		return ResponseEntity.ok(physiotherapistService.getAll());
	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping("/{id}")
	public ResponseEntity<PhysiotherapistDto> getOne(@PathVariable("id") Long id) {
		Optional<PhysiotherapistDto> physiotherapist = physiotherapistService.getById(id);

		if (physiotherapist.isPresent()) {
			return ResponseEntity.ok(physiotherapist.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Secured({ "ROLE_ADMIN" })
	@PutMapping("/{id}")
	public ResponseEntity<PhysiotherapistDto> update(@PathVariable Long id,
			@RequestBody Physiotherapist physiotherapist) {

		PhysiotherapistDto physiotherapistDto = physiotherapistService.update(id, physiotherapist);

		return ResponseEntity.ok(physiotherapistDto);
	}

}
