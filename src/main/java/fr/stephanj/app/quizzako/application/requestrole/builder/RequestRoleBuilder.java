package fr.stephanj.app.quizzako.application.requestrole.builder;

import fr.stephanj.app.quizzako.domain.RequestRole;
import fr.stephanj.app.quizzako.domain.Role;
import fr.stephanj.app.quizzako.domain.User;

public class RequestRoleBuilder {

	public static RequestRole build(Role role, User user) {
		return new RequestRole(user, role);
	}
}
