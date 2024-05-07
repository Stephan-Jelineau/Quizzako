package fr.stephanj.app.quizzako.infrastructure.user.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import fr.stephanj.app.quizzako.application.user.outbound.UserRepository;
import fr.stephanj.app.quizzako.domain.User;
import fr.stephanj.app.quizzako.domain.exception.user.UserAlreadyExistsException;
import fr.stephanj.app.quizzako.domain.exception.user.UserNotFoundException;
import fr.stephanj.app.quizzako.infrastructure.user.entity.UserEntity;
import fr.stephanj.app.quizzako.infrastructure.user.mapper.UserEntityMapper;

@Transactional
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private JpaUserRepository jpaUserRepo;

	@Override
	public String registerNewUser(User user) throws UserAlreadyExistsException {
		if (existsByEmail(user.getEmail()))
			throw new UserAlreadyExistsException("User already exists, cannot save");
		UserEntity userEntity = UserEntityMapper.toEntity(user);
		jpaUserRepo.save(userEntity);
		return user.getEmail();
	}

	@Override
	public void deleteUserByEmail(String email) throws UserNotFoundException {
		if (!existsByEmail(email))
			throw new UserNotFoundException("User not found, cannot delete");
		jpaUserRepo.deleteByEmail(email);
	}

	@Override
	public User getUserByEmail(String email) throws UserNotFoundException {
		UserEntity entity = jpaUserRepo.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("User not found"));
		return UserEntityMapper.toDomain(entity);
	}

	@Override
	public boolean existsByEmail(String email) {
		return jpaUserRepo.existsByEmail(email);
	}

	@Override
	public User getUserById(Long userId) throws UserNotFoundException {
		UserEntity entity = jpaUserRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found by id"));
		return UserEntityMapper.toDomain(entity);
	}

	@Override
	public void updateUserWithoutNewMail(User user) throws UserNotFoundException {
		if (!existsByEmail(user.getEmail()))
			throw new UserNotFoundException("User not found, cannot update");
		UserEntity entity = UserEntityMapper.toEntity(user);
		jpaUserRepo.save(entity);
	}

	@Override
	public void updateUserWithNewMail(User user, String oldEmail) throws UserAlreadyExistsException {
		String newEmail = user.getEmail();
		if (!oldEmail.equals(newEmail) && existsByEmail(newEmail))
			throw new UserAlreadyExistsException("The email " + newEmail + " is already associated to an account");
		UserEntity entity = UserEntityMapper.toEntity(user);
		jpaUserRepo.save(entity);
	}

}
