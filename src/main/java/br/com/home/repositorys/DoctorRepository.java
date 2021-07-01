package br.com.home.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.home.models.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
