package fr.stephanj.app.quizzako.domain.user.repository;

import fr.stephanj.app.quizzako.domain.user.model.User;

public interface UserRepository {
	User save(User user);

	void deleteByEmail(String email);

	User findByEmail(String email);
}
