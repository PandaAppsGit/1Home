package br.com.home.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.home.dtos.IndicationDto;
import br.com.home.models.Indication;
import br.com.home.repositorys.IndicationRepository;

@Service
public class IndicationService {

	@Autowired
	IndicationRepository repository;

	public IndicationDto save(Indication indication) {

		return IndicationDto.create(repository.save(indication));
	}

	public List<IndicationDto> getAll() {
		List<Indication> indications = repository.findAll();

		List<IndicationDto> list = new ArrayList<>();

		for (Indication d : indications) {
			list.add(IndicationDto.create(d));
		}

		return list;
	}

	public Optional<IndicationDto> getById(Long id) {
		Optional<Indication> indication = repository.findById(id);

		if (indication.isPresent()) {
			return Optional.of(IndicationDto.create(indication.get()));
		} else {
			return null;
		}
	}

	public IndicationDto update(Long id, Indication indication) {

		Optional<Indication> optional = repository.findById(id);

		if (optional.isPresent()) {
			Indication db = optional.get();

			db.setClientContact(indication.getClientContact());
			db.setClientName(indication.getClientName());
			db.setDiagnostic(indication.getDiagnostic());
			db.setDoctor(indication.getDoctor());
			db.setObservation(indication.getObservation());
			db.setReferralPhoto(indication.getReferralPhoto());

			repository.save(db);
			return IndicationDto.create(db);
		} else {
			return null;
		}
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

}
