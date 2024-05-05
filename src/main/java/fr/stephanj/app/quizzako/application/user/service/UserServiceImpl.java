package fr.stephanj.app.quizzako.application.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.stephanj.app.quizzako.domain.user.model.User;
import fr.stephanj.app.quizzako.infrastructure.user.persistence.UserRepository;
import fr.stephanj.app.quizzako.infrastructure.user.security.EncryptionService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EncryptionService passwordEncryptionService;

	@Override
	public User registerNewUser(User user) {
		String pwdEncrypted = passwordEncryptionService.encode(user.getPassword());
		user.setPassword(pwdEncrypted);
		return userRepository.create(user);
	}

	@Override
	public void deleteUserByEmail(String email) {
		userRepository.deleteByEmail(email);
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public void updateUser(User user) {
		userRepository.update(user);
	}

}
