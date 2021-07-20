package br.com.home.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.home.dtos.EvaluationDto;
import br.com.home.models.Evaluation;
import br.com.home.repositorys.EvaluationRepository;

@Service
public class EvaluationService {

	@Autowired
	EvaluationRepository repository;

	public EvaluationDto save(Evaluation evaluation) {

		return EvaluationDto.create(repository.save(evaluation));
	}

	public List<EvaluationDto> getAll() {
		List<Evaluation> evaluations = repository.findAll();

		List<EvaluationDto> list = new ArrayList<>();

		for (Evaluation d : evaluations) {
			list.add(EvaluationDto.create(d));
		}

		return list;
	}

	public Optional<EvaluationDto> getById(Long id) {
		Optional<Evaluation> evaluation = repository.findById(id);

		if (evaluation.isPresent()) {
			return Optional.of(EvaluationDto.create(evaluation.get()));
		} else {
			return null;
		}
	}

	public EvaluationDto update(Long id, Evaluation evaluation) {

		Optional<Evaluation> optional = repository.findById(id);

		if (optional.isPresent()) {
			Evaluation db = optional.get();

			db.setBody(evaluation.getBody());
			db.setDate(evaluation.getDate());

			repository.save(db);
			return EvaluationDto.create(db);
		} else {
			return null;
		}
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

}
