package br.com.home.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.home.models.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
