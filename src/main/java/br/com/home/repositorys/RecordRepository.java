package br.com.home.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.home.models.Record;

public interface RecordRepository extends JpaRepository<Record, Long> {

}
