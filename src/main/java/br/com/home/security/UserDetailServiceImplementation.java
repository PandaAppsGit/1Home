package br.com.home.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.home.models.DadUser;
import br.com.home.repositorys.DadUserRepository;

@Service(value = "userDetailsService")
public class UserDetailServiceImplementation implements UserDetailsService {

	@Autowired
	private DadUserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		DadUser dadUser = repository.findByUsername(username);

		if (dadUser == null) {
			throw new UsernameNotFoundException("user not found");
		}

		return dadUser;
	}

}
