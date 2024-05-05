package fr.stephanj.app.quizzako.application.requestrole.mapper;

import fr.stephanj.app.quizzako.domain.requestrole.model.RequestRole;
import fr.stephanj.app.quizzako.domain.user.model.Role;
import fr.stephanj.app.quizzako.infrastructure.requestrole.entity.RequestRoleEntity;

public class RequestRoleMapper {

	public static RequestRole toDomainRequestRole(Role role, Long id) {
		return new RequestRole(id, role);
	}

	public static RequestRole toDomainRequestRole(RequestRoleEntity entity) {
		return new RequestRole(entity.getUser().getId(), Role.valueOf(entity.getRoleResquested()));
	}
}
