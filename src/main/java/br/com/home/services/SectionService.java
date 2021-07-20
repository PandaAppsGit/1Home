package br.com.home.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.home.dtos.SectionDto;
import br.com.home.models.Section;
import br.com.home.repositorys.SectionRepository;

@Service
public class SectionService {

	@Autowired
	SectionRepository repository;

	public SectionDto save(Section section) {

		return SectionDto.create(repository.save(section));
	}

	public List<SectionDto> getAll() {
		List<Section> sections = repository.findAll();

		List<SectionDto> list = new ArrayList<>();

		for (Section d : sections) {
			list.add(SectionDto.create(d));
		}

		return list;
	}

	public List<SectionDto> getByPhysiotherapist(Long id) {
		List<Section> sections = repository.findByPhysiotherapist(id);

		List<SectionDto> list = new ArrayList<>();

		for (Section d : sections) {
			list.add(SectionDto.create(d));
		}

		return list;
	}

	public List<SectionDto> getByClient(Long id) {
		List<Section> sections = repository.findByClient(id);

		List<SectionDto> list = new ArrayList<>();

		for (Section d : sections) {
			list.add(SectionDto.create(d));
		}

		return list;
	}

	public Optional<SectionDto> getById(Long id) {
		Optional<Section> section = repository.findById(id);

		if (section.isPresent()) {
			return Optional.of(SectionDto.create(section.get()));
		} else {
			return null;
		}
	}

	public SectionDto update(Long id, Section section) {

		Optional<Section> optional = repository.findById(id);

		if (optional.isPresent()) {
			Section db = optional.get();

			db.setDate(section.getDate());
			db.setStatus(section.getStatus());

			repository.save(db);
			return SectionDto.create(db);
		} else {
			return null;
		}
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

}
