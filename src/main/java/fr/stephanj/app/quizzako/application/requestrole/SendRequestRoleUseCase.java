package fr.stephanj.app.quizzako.application.requestrole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.stephanj.app.quizzako.application.requestrole.builder.RequestRoleBuilder;
import fr.stephanj.app.quizzako.domain.RequestRole;
import fr.stephanj.app.quizzako.domain.Role;
import fr.stephanj.app.quizzako.domain.User;
import fr.stephanj.app.quizzako.domain.repository.RequestRoleRepository;
import fr.stephanj.app.quizzako.domain.repository.UserRepository;

@Component
public class SendRequestRoleUseCase {

	@Autowired
	RequestRoleRepository requestRoleRepository;

	@Autowired
	UserRepository userRepository;

	public void requestRoleForUser(String email, String role) {
		User user = userRepository.getUserByEmail(email);
		RequestRole requestRole = RequestRoleBuilder.build(Role.valueOf(role), user);
		requestRoleRepository.saveRequestRole(requestRole);
	}

}
