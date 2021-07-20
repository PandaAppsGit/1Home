package br.com.home.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.home.models.Evaluation;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

}
