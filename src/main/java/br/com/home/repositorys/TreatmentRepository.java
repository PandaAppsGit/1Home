package br.com.home.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.home.models.Treatment;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {

}
