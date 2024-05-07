package fr.stephanj.app.quizzako.infrastructure.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.stephanj.app.quizzako.application.user.outbound.EncryptionService;

public class EncryptionServiceImpl implements EncryptionService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public String encode(String password) {
		return passwordEncoder.encode(password);
	}
}
