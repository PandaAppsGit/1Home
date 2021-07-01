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
import br.com.home.models.Doctor;
import br.com.home.services.DadUserService;

@RestController
@RequestMapping("api/v1/doctors")
public class DoctorController {

	@Autowired
	DadUserService dadUserService;

	@PostMapping("/sign-up")
	public ResponseEntity<URI> signUp(@RequestBody Doctor doctor, HttpServletRequest request) {

		DadUserDto dadUser = dadUserService.signUp(doctor, "ROLE_DOCTOR", request);

		URI location = getUri(dadUser.getId());

		return ResponseEntity.created(location).build();

	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

}
