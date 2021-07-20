package br.com.home.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.home.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

	@Query(value = "SELECT * FROM dad_user u WHERE u.doctor_id = ?", nativeQuery = true)
	List<Client> findByDoctor(Long id);

	@Query(value = "SELECT * FROM dad_user u WHERE u.physiotherapist_id = ?", nativeQuery = true)
	List<Client> findByPhysiotherapist(Long id);

}
