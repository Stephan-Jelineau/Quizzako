package fr.stephanj.app.quizzako.infrastructure.user.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.stephanj.app.quizzako.application.user.exception.UserAlreadyExistsException;
import fr.stephanj.app.quizzako.application.user.exception.UserNotFoundException;
import fr.stephanj.app.quizzako.domain.user.model.User;
import fr.stephanj.app.quizzako.infrastructure.user.entity.UserEntity;
import fr.stephanj.app.quizzako.infrastructure.user.mapper.UserMapper;

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
		UserEntity entitySaved = jpaUserRepo.save(userEntity);
		return UserMapper.toDomainUser(entitySaved);
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
	public User update(User user) {
		UserEntity userEntity = jpaUserRepo.findById(user.getId())
				.orElseThrow(() -> new UserNotFoundException("User not found, cannot update"));
		userEntity.setFirstname(user.getFirstname());
		userEntity.setName(user.getName());
		userEntity.setEmail(user.getEmail());
		UserEntity entitySaved = jpaUserRepo.save(userEntity);
		return UserMapper.toDomainUser(entitySaved);
	}

	@Override
	public boolean existsByEmail(String email) {
		return jpaUserRepo.existsByEmail(email);
	}

}
