package br.com.home.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.home.models.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {

}
