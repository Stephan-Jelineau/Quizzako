package fr.stephanj.app.quizzako.infrastructure.user.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.stephanj.app.quizzako.application.user.exception.UserAlreadyExistsException;
import fr.stephanj.app.quizzako.application.user.exception.UserNotFoundException;
import fr.stephanj.app.quizzako.application.user.mapper.UserMapper;
import fr.stephanj.app.quizzako.domain.user.model.User;
import fr.stephanj.app.quizzako.infrastructure.user.entity.UserEntity;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private JpaUserRepository jpaUserRepo;

	@Override
	public User create(User user) {
		if (jpaUserRepo.existsByEmail(user.getEmail()))
			throw new UserAlreadyExistsException("User already exists, cannot save");
		UserEntity userEntity = new UserEntity(user);
		jpaUserRepo.save(userEntity);
		return user;
	}

	@Override
	public void deleteByEmail(String email) {
		if (!jpaUserRepo.existsByEmail(email))
			throw new UserNotFoundException("User not found, cannot delete");
		jpaUserRepo.deleteByEmail(email);
	}

	@Override
	public User findByEmail(String email) {
		UserEntity userEntity = jpaUserRepo.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return UserMapper.toDomainUser(userEntity);
	}

	@Override
	public void update(User user) {
		UserEntity entity = new UserEntity(user);
		jpaUserRepo.save(entity);
	}

	@Override
	public boolean existsByEmail(String email) {
		return jpaUserRepo.existsByEmail(email);
	}

	@Override
	public User findById(Long userId) {
		UserEntity entity = jpaUserRepo.findById(userId)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return UserMapper.toDomainUser(entity);
	}

}
