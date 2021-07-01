package br.com.home.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.home.repositorys.AdminRepository;

@Service
public class AdminService {

	@Autowired
	AdminRepository repository;

}
