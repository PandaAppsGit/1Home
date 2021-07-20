package br.com.home.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.home.dtos.TreatmentDto;
import br.com.home.models.Treatment;
import br.com.home.repositorys.TreatmentRepository;

@Service
public class TreatmentService {

	@Autowired
	TreatmentRepository repository;

	public TreatmentDto save(Treatment treatment) {

		return TreatmentDto.create(repository.save(treatment));
	}

	public List<TreatmentDto> getAll() {
		List<Treatment> treatments = repository.findAll();

		List<TreatmentDto> list = new ArrayList<>();

		for (Treatment d : treatments) {
			list.add(TreatmentDto.create(d));
		}

		return list;
	}

	public Optional<TreatmentDto> getById(Long id) {
		Optional<Treatment> treatment = repository.findById(id);

		if (treatment.isPresent()) {
			return Optional.of(TreatmentDto.create(treatment.get()));
		} else {
			return null;
		}
	}

	public TreatmentDto update(Long id, Treatment treatment) {

		Optional<Treatment> optional = repository.findById(id);

		if (optional.isPresent()) {
			Treatment db = optional.get();

			db.setDescription(treatment.getDescription());
			db.setImage(treatment.getImage());
			db.setName(treatment.getName());

			repository.save(db);
			return TreatmentDto.create(db);
		} else {
			return null;
		}
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

}
