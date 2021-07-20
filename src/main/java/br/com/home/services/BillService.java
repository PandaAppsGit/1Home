package br.com.home.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.home.dtos.BillDto;
import br.com.home.models.Bill;
import br.com.home.repositorys.BillRepository;

@Service
public class BillService {

	@Autowired
	BillRepository repository;

	public BillDto save(Bill bill) {

		return BillDto.create(repository.save(bill));
	}

	public List<BillDto> getAll() {
		List<Bill> bills = repository.findAll();

		List<BillDto> list = new ArrayList<>();

		for (Bill d : bills) {
			list.add(BillDto.create(d));
		}

		return list;
	}

	public Optional<BillDto> getById(Long id) {
		Optional<Bill> bill = repository.findById(id);

		if (bill.isPresent()) {
			return Optional.of(BillDto.create(bill.get()));
		} else {
			return null;
		}
	}

	public BillDto update(Long id, Bill bill) {

		Optional<Bill> optional = repository.findById(id);

		if (optional.isPresent()) {
			Bill db = optional.get();

			db.setDate(bill.getDate());
			db.setPdf(bill.getPdf());
			db.setStatus(bill.getStatus());
			db.setPrice(bill.getPrice());

			repository.save(db);
			return BillDto.create(db);
		} else {
			return null;
		}
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

}
