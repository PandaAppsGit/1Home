package br.com.home.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.home.dtos.DoctorDto;
import br.com.home.models.Doctor;
import br.com.home.repositorys.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
	DoctorRepository repository;

	public List<DoctorDto> getAll() {
		List<Doctor> doctors = repository.findAll();

		List<DoctorDto> list = new ArrayList<>();

		for (Doctor d : doctors) {
			list.add(DoctorDto.create(d));
		}

		return list;
	}

	public Optional<DoctorDto> getById(Long id) {
		Optional<Doctor> doctor = repository.findById(id);

		if (doctor.isPresent()) {
			return Optional.of(DoctorDto.create(doctor.get()));
		} else {
			return null;
		}
	}

	public DoctorDto update(Long id, Doctor doctor) {

		Optional<Doctor> optional = repository.findById(id);

		if (optional.isPresent()) {
			Doctor db = optional.get();

			db.setCrm(doctor.getCrm());

			repository.save(db);
			return DoctorDto.create(db);
		} else {
			return null;
		}
	}

}
