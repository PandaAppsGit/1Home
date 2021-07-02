package br.com.home.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.home.dtos.PhysiotherapistDto;
import br.com.home.models.Physiotherapist;
import br.com.home.repositorys.PhysiotherapistRepository;

@Service
public class PhysiotherapistService {

	@Autowired
	PhysiotherapistRepository repository;

	public List<PhysiotherapistDto> getAll() {
		List<Physiotherapist> physiotherapists = repository.findAll();

		List<PhysiotherapistDto> list = new ArrayList<>();

		for (br.com.home.models.Physiotherapist p : physiotherapists) {
			list.add(PhysiotherapistDto.create(p));
		}

		return list;
	}

	public Optional<PhysiotherapistDto> getById(Long id) {
		Optional<Physiotherapist> physiotherapist = repository.findById(id);

		if (physiotherapist.isPresent()) {
			return Optional.of(PhysiotherapistDto.create(physiotherapist.get()));
		} else {
			return null;
		}
	}

	public PhysiotherapistDto update(Long id, Physiotherapist physiotherapist) {

		Optional<Physiotherapist> optional = repository.findById(id);

		if (optional.isPresent()) {
			Physiotherapist db = optional.get();

			db.setCrefito(physiotherapist.getCrefito());

			repository.save(db);
			return PhysiotherapistDto.create(db);
		} else {
			return null;
		}
	}

}
