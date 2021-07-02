package br.com.home;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.home.models.DadUser;
import br.com.home.models.Role;
import br.com.home.services.DadUserService;
import br.com.home.services.RoleService;

@Component
public class DataLoader implements CommandLineRunner {

	private RoleService service;

	private DadUserService dadUserService;

	public DataLoader(RoleService service, DadUserService dadUserService) {
		this.service = service;
		this.dadUserService = dadUserService;
	}

	@Override
	public void run(String... args) throws Exception {
		initRoles();
		createAdmin();
	}

	private void initRoles() {

		Optional<Role> role1 = service.getRoleById(1L);
		Optional<Role> role2 = service.getRoleById(2L);
		Optional<Role> role3 = service.getRoleById(3L);
		Optional<Role> role4 = service.getRoleById(4L);

		if (role1 == null) {

			Role roleClient = new Role();

			roleClient.setId(1L);
			roleClient.setName("ROLE_CLIENT");

			service.saveRole(roleClient);
		}

		if (role2 == null) {
			Role roleDoctor = new Role();

			roleDoctor.setId(2L);
			roleDoctor.setName("ROLE_DOCTOR");

			service.saveRole(roleDoctor);
		}

		if (role3 == null) {
			Role rolePhysiotherapist = new Role();

			rolePhysiotherapist.setId(3L);
			rolePhysiotherapist.setName("ROLE_PHYSIOTHERAPIST");

			service.saveRole(rolePhysiotherapist);
		}

		if (role4 == null) {
			Role roleAdmin = new Role();

			roleAdmin.setId(4L);
			roleAdmin.setName("ROLE_ADMIN");

			service.saveRole(roleAdmin);
		}

	}

	private void createAdmin() {
		Optional<DadUser> dadUser = dadUserService.getById(1L);

		if (!dadUser.isPresent()) {

			DadUser admin = new DadUser();

			admin.setId(1L);
			admin.setCellphone("9999999999");
			admin.setEmail("admin@admin.com");
			admin.setName("Admin");
			admin.setPassword("admin");
			admin.setUsername("admin");

			dadUserService.adminSignUp(admin);
		}
	}
}
