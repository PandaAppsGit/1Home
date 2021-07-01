package br.com.home.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.home.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);

}
