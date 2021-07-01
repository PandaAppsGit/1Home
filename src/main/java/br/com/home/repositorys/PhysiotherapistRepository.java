package br.com.home.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.home.models.Physiotherapist;

public interface PhysiotherapistRepository extends JpaRepository<Physiotherapist, Long> {

}
