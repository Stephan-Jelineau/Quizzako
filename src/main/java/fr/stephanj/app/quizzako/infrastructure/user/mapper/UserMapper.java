package fr.stephanj.app.quizzako.infrastructure.user.mapper;

import fr.stephanj.app.quizzako.application.user.request.CreateUserRequest;
import fr.stephanj.app.quizzako.domain.user.model.Role;
import fr.stephanj.app.quizzako.domain.user.model.User;
import fr.stephanj.app.quizzako.infrastructure.user.entity.UserEntity;

public class UserMapper {

	public static User toDomainUser(CreateUserRequest userForm) {
		return new User(userForm.getFirstname(), userForm.getName(), userForm.getEmail(), userForm.getPassword1(),
				Role.USER);
	}

	public static User toDomainUser(UserEntity userEntity) {
		return new User(userEntity.getId(), userEntity.getFirstname(), userEntity.getName(), userEntity.getEmail(),
				userEntity.getPassword(), Role.valueOf(userEntity.getRole()));
	}
}
