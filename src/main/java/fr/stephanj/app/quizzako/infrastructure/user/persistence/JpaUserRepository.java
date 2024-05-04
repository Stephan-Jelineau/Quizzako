package fr.stephanj.app.quizzako.infrastructure.user.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.stephanj.app.quizzako.infrastructure.user.entity.UserEntity;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByEmail(String email);

	boolean existsByEmail(String email);

	void deleteByEmail(String email);
}
