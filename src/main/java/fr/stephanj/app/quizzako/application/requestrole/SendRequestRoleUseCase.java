package fr.stephanj.app.quizzako.application.requestrole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.stephanj.app.quizzako.application.requestrole.mapper.RequestRoleMapper;
import fr.stephanj.app.quizzako.application.requestrole.outbound.RequestRoleRepository;
import fr.stephanj.app.quizzako.application.user.outbound.UserRepository;
import fr.stephanj.app.quizzako.domain.RequestRole;
import fr.stephanj.app.quizzako.domain.Role;
import fr.stephanj.app.quizzako.domain.User;

@Component
public class SendRequestRoleUseCase {

	@Autowired
	RequestRoleRepository requestRoleRepository;

	@Autowired
	UserRepository userRepository;

	public void requestRoleForUser(String email, String role) {
		User user = userRepository.getUserByEmail(email);
		RequestRole requestRole = RequestRoleMapper.toDomainObj(Role.valueOf(role), user);
		requestRoleRepository.saveRequestRole(requestRole);
	}

}
