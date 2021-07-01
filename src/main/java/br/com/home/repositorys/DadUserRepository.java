package br.com.home.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.home.models.DadUser;

public interface DadUserRepository extends JpaRepository<DadUser, Long> {

	DadUser findByUsername(String username);

	DadUser findByEmail(String email);

	DadUser findByAccountActivationToken(String accountActivationToken);

	DadUser findByPasswordRecoveryToken(String accountActivationToken);

}
