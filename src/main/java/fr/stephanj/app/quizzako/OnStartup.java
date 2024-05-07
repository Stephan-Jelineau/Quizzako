package fr.stephanj.app.quizzako;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import fr.stephanj.app.quizzako.application.user.outbound.EncryptionService;
import fr.stephanj.app.quizzako.application.user.outbound.UserRepository;
import fr.stephanj.app.quizzako.domain.Role;
import fr.stephanj.app.quizzako.domain.User;

@Component
public class OnStartup implements ApplicationRunner {

	private static final String ADMIN_EMAIL = "admin@admin.fr";

	@Autowired
	UserRepository userRepository;

	@Autowired
	EncryptionService encryptionService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		createAdminUser();
	}

	private void createAdminUser() {
		if (userRepository.existsByEmail(ADMIN_EMAIL))
			return;
		User user = new User("admin", "admin", ADMIN_EMAIL, encryptionService.encode("admin"), Role.ADMIN);
		userRepository.registerNewUser(user);
	}

}
