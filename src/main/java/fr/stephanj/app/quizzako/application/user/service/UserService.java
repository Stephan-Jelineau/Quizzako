package fr.stephanj.app.quizzako.application.user.service;

import fr.stephanj.app.quizzako.domain.user.model.User;

public interface UserService {
	User registerNewUser(User user);

	void deleteUserByEmail(String email);

	User getUserByEmail(String email);

	boolean existsByEmail(String email);

	void updateUser(User user);
}
