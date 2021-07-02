package br.com.home.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.home.models.Indication;

public interface IndicationRepository extends JpaRepository<Indication, Long> {

}
