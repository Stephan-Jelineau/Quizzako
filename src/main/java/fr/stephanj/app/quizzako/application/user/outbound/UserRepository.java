package fr.stephanj.app.quizzako.application.user.outbound;

import fr.stephanj.app.quizzako.domain.User;
import fr.stephanj.app.quizzako.domain.exception.user.UserAlreadyExistsException;
import fr.stephanj.app.quizzako.domain.exception.user.UserNotFoundException;

public interface UserRepository {
	String registerNewUser(User user) throws UserAlreadyExistsException;

	void deleteUserByEmail(String email) throws UserNotFoundException;

	User getUserByEmail(String email) throws UserNotFoundException;

	void updateUserWithoutNewMail(User user) throws UserNotFoundException;

	boolean existsByEmail(String email);

	User getUserById(Long userId) throws UserNotFoundException;

	void updateUserWithNewMail(User user, String oldEmail) throws UserAlreadyExistsException;
}
