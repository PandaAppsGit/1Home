package br.com.home.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.home.dtos.DadUserDto;
import br.com.home.models.DadUser;
import br.com.home.models.Role;
import br.com.home.repositorys.DadUserRepository;
import br.com.home.repositorys.RoleRepository;
import net.bytebuddy.utility.RandomString;

@Service
public class DadUserService {

	@Autowired
	DadUserRepository repository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	EmailService emailService;

	public DadUserDto signUp(DadUser dadUser, String roleName, HttpServletRequest request) {
		Assert.isTrue(repository.findByUsername(dadUser.getUsername()) == null,
				"O Login do usuário já está cadastrado.");
		;

		Assert.isTrue(repository.findByEmail(dadUser.getEmail()) == null, "O Email do usuário já está cadastrado.");
		;

		String accountActivationToken = RandomString.make(30);

		dadUser.setAccountActivationToken(accountActivationToken);

		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByName(roleName));
		dadUser.setRoles(roles);

		dadUser.setActive(false);

		dadUser.setPassword(passwordEncoder.encode(dadUser.getPassword()));

		String activationAccountLink = getSiteURL(request)
				+ "/api/v1/users/account-activation?account-activation-token=" + accountActivationToken;

		try {
			emailService.sendEmailAccountActivation(dadUser.getEmail(), activationAccountLink);
		} catch (Exception e) {
			System.out.println(e);
		}

		DadUser d = repository.save(dadUser);

		return DadUserDto.create(d);

	}

	public ResponseEntity<DadUserDto> ForgotPassword(HttpServletRequest request, DadUser dadUser) {

		try {
			String passwordRecoveryToken = RandomString.make(30);
			updateResetPasswordToken(passwordRecoveryToken, dadUser);
			String resetPasswordLink = getSiteURL(request) + "/api/v1/users/password-recovery?passwordRecoveryToken="
					+ passwordRecoveryToken;
			emailService.sendEmailPasswordRecovery(dadUser.getEmail(), resetPasswordLink);

			return ResponseEntity.accepted().build();
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}
	}

	public void updateResetPasswordToken(String passwordRecoveryToken, DadUser dadUser) {

		dadUser.setPasswordRecoveryToken(passwordRecoveryToken);
		repository.save(dadUser);
		DadUserDto.create(dadUser);
	}

	public DadUserDto changePassword(DadUser dadUser) {

		Optional<DadUser> optional = repository.findById(dadUser.getId());

		if (optional.isPresent()) {
			DadUser db = optional.get();

			db.setPassword(passwordEncoder.encode(dadUser.getPassword()));

			repository.save(db);
			return DadUserDto.create(db);
		} else {
			return null;
		}

	}

	public DadUserDto enableClient(DadUser dadUser) {
		dadUser.setActive(true);
		dadUser.setAccountActivationToken(null);

		return DadUserDto.create(repository.save(dadUser));
	}

	public DadUser getByEmail(String email) {
		return repository.findByEmail(email);
	}

	public DadUser getByUsername(String username) {
		return repository.findByUsername(username);
	}

	public DadUser getByAccountActivationToken(String accountActivationToken) {
		return repository.findByAccountActivationToken(accountActivationToken);
	}

	public DadUser getByPasswordRecoveryToken(String passwordRecoveryToken) {
		return repository.findByPasswordRecoveryToken(passwordRecoveryToken);
	}

	public static String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}

}
