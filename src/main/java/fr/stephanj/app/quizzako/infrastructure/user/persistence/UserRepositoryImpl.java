package fr.stephanj.app.quizzako.infrastructure.user.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import fr.stephanj.app.quizzako.domain.user.model.User;
import fr.stephanj.app.quizzako.domain.user.repository.UserRepository;
import fr.stephanj.app.quizzako.infrastructure.user.entity.UserEntity;
import fr.stephanj.app.quizzako.infrastructure.user.exception.UserAlreadyExistsException;
import fr.stephanj.app.quizzako.infrastructure.user.exception.UserNotFoundException;
import fr.stephanj.app.quizzako.infrastructure.user.mapper.UserMapper;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private JpaUserRepository userRepo;

	@Override
	public User save(User user) {
		if (userRepo.existsByEmail(user.getEmail()))
			throw new UserAlreadyExistsException("User already exists, cannot save");
		UserEntity userEntity = new UserEntity(user);
		UserEntity entitySaved = userRepo.save(userEntity);
		return UserMapper.toDomainUser(entitySaved);
	}

	@Override
	public void deleteByEmail(String email) {
		if (!userRepo.existsByEmail(email))
			throw new UserNotFoundException("User not found, cannot delete");
		userRepo.deleteByEmail(email);
	}

	@Override
	public User findByEmail(String email) {
		UserEntity userEntity = userRepo.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return UserMapper.toDomainUser(userEntity);
	}

}
