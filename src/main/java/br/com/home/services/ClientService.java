package br.com.home.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.home.dtos.ClientDto;
import br.com.home.models.Client;
import br.com.home.repositorys.ClientRepository;

@Service
public class ClientService {

	@Autowired
	ClientRepository repository;

	public List<ClientDto> getAll() {
		List<Client> clients = repository.findAll();

		List<ClientDto> list = new ArrayList<>();

		for (Client c : clients) {
			list.add(ClientDto.create(c));
		}

		return list;
	}

	public Optional<ClientDto> getById(Long id) {
		Optional<Client> client = repository.findById(id);

		if (client.isPresent()) {
			return Optional.of(ClientDto.create(client.get()));
		} else {
			return null;
		}
	}

	public List<ClientDto> getByDoctor(Long id) {
		List<Client> clients = repository.findByDoctor(id);

		List<ClientDto> list = new ArrayList<>();

		for (Client c : clients) {
			list.add(ClientDto.create(c));
		}

		return list;
	}

	public List<ClientDto> getByPhysiotherapist(Long id) {
		List<Client> clients = repository.findByPhysiotherapist(id);

		List<ClientDto> list = new ArrayList<>();

		for (Client c : clients) {
			list.add(ClientDto.create(c));
		}

		return list;
	}

}
