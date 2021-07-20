package br.com.home.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.home.models.Section;

public interface SectionRepository extends JpaRepository<Section, Long> {

	@Query(value = "SELECT * FROM section s WHERE s.physiotherapist_id = ?", nativeQuery = true)
	List<Section> findByPhysiotherapist(Long id);

	@Query(value = "SELECT * FROM section s WHERE s.client_id = ?", nativeQuery = true)
	List<Section> findByClient(Long id);

}
