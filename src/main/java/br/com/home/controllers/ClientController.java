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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.home.dtos.ClientDto;
import br.com.home.dtos.DadUserDto;
import br.com.home.models.Client;
import br.com.home.services.ClientService;
import br.com.home.services.DadUserService;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {

	@Autowired
	DadUserService dadUserService;

	@Autowired
	ClientService clientService;

	@PostMapping("/sign-up")
	public ResponseEntity<URI> signUp(@RequestBody Client client, HttpServletRequest request) {

		DadUserDto dadUser = dadUserService.signUp(client, "ROLE_CLIENT", request);

		URI location = getUri(dadUser.getId());

		return ResponseEntity.created(location).build();

	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping
	public ResponseEntity<List<ClientDto>> getAll() {
		return ResponseEntity.ok(clientService.getAll());
	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping("/{id}")
	public ResponseEntity<ClientDto> getOne(@PathVariable("id") Long id) {
		Optional<ClientDto> client = clientService.getById(id);

		if (client.isPresent()) {
			return ResponseEntity.ok(client.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
