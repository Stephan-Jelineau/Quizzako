package fr.stephanj.app.quizzako.application.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.stephanj.app.quizzako.domain.user.model.User;
import fr.stephanj.app.quizzako.domain.user.repository.UserRepository;
import fr.stephanj.app.quizzako.domain.user.service.UserService;
import fr.stephanj.app.quizzako.infrastructure.user.security.EncryptionServiceImpl;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	EncryptionServiceImpl passwordEncryptionService;

	@Override
	public User registerNewUser(User user) {
		user.encryptPassword(passwordEncryptionService);
		return userRepository.save(user);
	}

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void deleteUserByEmail(String email) {
		userRepository.deleteByEmail(email);
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
