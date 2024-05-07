package fr.stephanj.app.quizzako.infrastructure.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import fr.stephanj.app.quizzako.application.user.outbound.UserRepository;
import fr.stephanj.app.quizzako.infrastructure.user.entity.UserEntity;
import fr.stephanj.app.quizzako.infrastructure.user.mapper.UserEntityMapper;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity user = UserEntityMapper.toEntity(userRepository.getUserByEmail(email));
		UserBuilder builder = User.builder().username(email).password(user.getPassword())
				.roles(user.getRole().toString());
		return builder.build();
	}
}
