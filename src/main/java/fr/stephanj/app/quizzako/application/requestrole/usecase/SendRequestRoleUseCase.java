package fr.stephanj.app.quizzako.application.requestrole.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.stephanj.app.quizzako.application.requestrole.exception.RoleRequestedNotAllowedException;
import fr.stephanj.app.quizzako.application.requestrole.mapper.RequestRoleMapper;
import fr.stephanj.app.quizzako.application.requestrole.service.RequestRoleService;
import fr.stephanj.app.quizzako.application.user.service.UserServiceImpl;
import fr.stephanj.app.quizzako.domain.requestrole.model.RequestRole;
import fr.stephanj.app.quizzako.domain.user.model.Role;
import fr.stephanj.app.quizzako.domain.user.model.User;

@Component
public class SendRequestRoleUseCase {

	@Autowired
	RequestRoleService roleService;

	@Autowired
	UserServiceImpl userService;

	public void requestRoleForUser(String email, String role) {
		User user = userService.getUserByEmail(email);
		Role roleObj = Role.valueOf(role);

		if (!Role.isRequestableRole(roleObj))
			throw new RoleRequestedNotAllowedException("The requested role is not allowed");

		if (user.getRole().equals(role))
			return;

		RequestRole requestRole = RequestRoleMapper.toDomainRequestRole(roleObj, user);
		roleService.saveRequestRole(requestRole);
	}

}
