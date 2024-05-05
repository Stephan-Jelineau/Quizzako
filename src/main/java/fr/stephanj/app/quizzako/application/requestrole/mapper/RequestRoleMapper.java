package fr.stephanj.app.quizzako.application.requestrole.mapper;

import fr.stephanj.app.quizzako.application.user.mapper.UserMapper;
import fr.stephanj.app.quizzako.domain.requestrole.model.RequestRole;
import fr.stephanj.app.quizzako.domain.user.model.Role;
import fr.stephanj.app.quizzako.domain.user.model.User;
import fr.stephanj.app.quizzako.infrastructure.requestrole.entity.RequestRoleEntity;

public class RequestRoleMapper {

	public static RequestRole toDomainRequestRole(Role role, User user) {
		return new RequestRole(user, role);
	}

	public static RequestRole toDomainRequestRole(RequestRoleEntity entity) {
		return new RequestRole(entity.getId(), UserMapper.toDomainUser(entity.getUser()),
				Role.valueOf(entity.getRoleResquested()), entity.isActive(), entity.getOpenDate(),
				entity.getCloseDate());

	}
}
