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
import br.com.home.dtos.DoctorDto;
import br.com.home.models.Doctor;
import br.com.home.services.DadUserService;
import br.com.home.services.DoctorService;

@RestController
@RequestMapping("api/v1/doctors")
public class DoctorController {

	@Autowired
	DadUserService dadUserService;

	@Autowired
	DoctorService doctorService;

	@PostMapping("/sign-up")
	public ResponseEntity<URI> signUp(@RequestBody Doctor doctor, HttpServletRequest request) {

		DadUserDto dadUser = dadUserService.signUp(doctor, "ROLE_DOCTOR", request);

		URI location = getUri(dadUser.getId());

		return ResponseEntity.created(location).build();

	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

	@Secured({ "ROLE_ADMIN" })
	@PutMapping("/{id}")
	public ResponseEntity<DoctorDto> update(@PathVariable Long id, @RequestBody Doctor doctor) {

		DoctorDto doctorDto = doctorService.update(id, doctor);

		return ResponseEntity.ok(doctorDto);
	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping
	public ResponseEntity<List<DoctorDto>> getAll() {
		return ResponseEntity.ok(doctorService.getAll());
	}

	@Secured({ "ROLE_ADMIN" })
	@GetMapping("/{id}")
	public ResponseEntity<DoctorDto> getOne(@PathVariable("id") Long id) {
		Optional<DoctorDto> doctor = doctorService.getById(id);

		if (doctor.isPresent()) {
			return ResponseEntity.ok(doctor.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
