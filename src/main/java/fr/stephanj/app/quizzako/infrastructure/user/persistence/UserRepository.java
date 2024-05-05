package fr.stephanj.app.quizzako.infrastructure.user.persistence;

import fr.stephanj.app.quizzako.domain.user.model.User;

public interface UserRepository {
	User create(User user);

	void deleteByEmail(String email);

	User findByEmail(String email);
	
	void update(User user);
	
	boolean existsByEmail(String email);

	User findById(Long userId);
}
