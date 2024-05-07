package fr.stephanj.app.quizzako.infrastructure.user.mapper;

import fr.stephanj.app.quizzako.domain.Role;
import fr.stephanj.app.quizzako.domain.User;
import fr.stephanj.app.quizzako.infrastructure.user.entity.UserEntity;

public class UserEntityMapper {

	public static User toDomain(UserEntity userEntity) {
		return new User(userEntity.getId(), userEntity.getFirstname(), userEntity.getName(), userEntity.getEmail(),
				userEntity.getPassword(), Role.valueOf(userEntity.getRole()));
	}

	public static UserEntity toEntity(User user) {
		return new UserEntity(user.getId(), user.getEmail(), user.getFirstname(), user.getName(), user.getPassword(),
				user.getRole().toString());
	}

}
