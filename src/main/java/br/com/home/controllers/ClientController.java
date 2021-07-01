package br.com.home.controllers;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.home.dtos.DadUserDto;
import br.com.home.models.Client;
import br.com.home.services.DadUserService;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {

	@Autowired
	DadUserService dadUserService;

	@PostMapping("/sign-up")
	public ResponseEntity<URI> signUp(@RequestBody Client client, HttpServletRequest request) {

		DadUserDto dadUser = dadUserService.signUp(client, "ROLE_CLIENT", request);

		URI location = getUri(dadUser.getId());

		return ResponseEntity.created(location).build();

	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

}
