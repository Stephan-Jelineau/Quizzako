package fr.stephanj.app.quizzako;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import fr.stephanj.app.quizzako.application.user.service.UserService;
import fr.stephanj.app.quizzako.domain.user.model.Role;
import fr.stephanj.app.quizzako.domain.user.model.User;

@Component
public class OnStartup implements ApplicationRunner {

	@Autowired
	UserService service;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		createAdminUser();
	}

	private void createAdminUser() {
		User user = new User("admin", "admin", "admin@admin.fr", "admin", Role.ADMIN);
		if(service.existsByEmail(user.getEmail()))
			return;
		service.registerNewUser(user);
	}

}
