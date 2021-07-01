package br.com.home.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.home.models.Role;
import br.com.home.repositorys.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository repository;

	public void saveRole(Role role) {

		repository.save(role);
	}

	public Optional<Role> getRoleById(Long id) {
		Optional<Role> role = repository.findById(id);
		if (role.isPresent()) {
			return Optional.of(role.get());
		} else {
			return null;
		}
	}

}
