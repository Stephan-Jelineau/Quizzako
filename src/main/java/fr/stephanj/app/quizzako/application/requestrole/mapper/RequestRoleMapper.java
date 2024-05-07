package fr.stephanj.app.quizzako.application.requestrole.mapper;

import fr.stephanj.app.quizzako.domain.RequestRole;
import fr.stephanj.app.quizzako.domain.Role;
import fr.stephanj.app.quizzako.domain.User;

public class RequestRoleMapper {

	public static RequestRole toDomainObj(Role role, User user) {
		return new RequestRole(user, role);
	}
}
