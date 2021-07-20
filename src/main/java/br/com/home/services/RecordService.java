package br.com.home.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.home.dtos.RecordDto;
import br.com.home.models.Record;
import br.com.home.repositorys.RecordRepository;

@Service
public class RecordService {

	@Autowired
	RecordRepository repository;

	public RecordDto save(Record record) {

		return RecordDto.create(repository.save(record));
	}

	public List<RecordDto> getAll() {
		List<Record> records = repository.findAll();

		List<RecordDto> list = new ArrayList<>();

		for (Record d : records) {
			list.add(RecordDto.create(d));
		}

		return list;
	}

	public Optional<RecordDto> getById(Long id) {
		Optional<Record> record = repository.findById(id);

		if (record.isPresent()) {
			return Optional.of(RecordDto.create(record.get()));
		} else {
			return null;
		}
	}

	public RecordDto update(Long id, Record record) {

		Optional<Record> optional = repository.findById(id);

		if (optional.isPresent()) {
			Record db = optional.get();

			db.setBirthDate(record.getBirthDate());
			db.setGender(record.getGender());
			db.setHeight(record.getHeight());
			db.setWeight(record.getWeight());

			repository.save(db);
			return RecordDto.create(db);
		} else {
			return null;
		}
	}

}
