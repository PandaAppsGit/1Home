package br.com.home.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.home.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
