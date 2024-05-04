package fr.stephanj.app.quizzako.application.user.service;

import fr.stephanj.app.quizzako.domain.user.model.User;
import fr.stephanj.app.quizzako.presentation.user.request.ViewAndUpdateUserRequest;

public interface UserService {
	User registerNewUser(User user);

	void updateUser(User user, ViewAndUpdateUserRequest userRequest);

	void deleteUserByEmail(String email);

	User getUserByEmail(String email);
	
	boolean isMailNewAndAlreadyExisting(String emailFromAuthenticatedUser, String emailFromRequest);
}
