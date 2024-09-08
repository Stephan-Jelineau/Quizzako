package fr.stephanj.app.quizzako.infrastructure.requestrole.mapper;

import fr.stephanj.app.quizzako.domain.RequestRole;
import fr.stephanj.app.quizzako.domain.Role;
import fr.stephanj.app.quizzako.infrastructure.requestrole.entity.RequestRoleEntity;
import fr.stephanj.app.quizzako.infrastructure.user.mapper.UserEntityMapper;

public class RequestRoleEntityMapper {

	public static RequestRole toDomain(RequestRoleEntity entity) {
		return new RequestRole(entity.getId(), UserEntityMapper.toDomain(entity.getUser()),
				Role.valueOf(entity.getRoleResquested()), entity.isActive(), entity.getOpenDate(),
				entity.getCloseDate());

	}

	public static RequestRoleEntity toEntity(RequestRole requestRole) {
		return new RequestRoleEntity(requestRole.getId(), UserEntityMapper.toEntity(requestRole.getUser()),
				requestRole.getRoleResquested().toString(), requestRole.isActive(), requestRole.getOpenDate(),
				requestRole.getCloseDate());
	}
}
