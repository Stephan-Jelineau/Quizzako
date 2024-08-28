package fr.stephanj.app.quizzako.infrastructure.user.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.stephanj.app.quizzako.infrastructure.user.entity.UserEntity;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByEmail(String email);

	boolean existsByEmail(String email);

	void deleteByEmail(String email);
	
	@Query("SELECT u.id FROM UserEntity u WHERE u.email = :email")
	Optional<Long> getIdByEmail(@Param("email") String email);

	@Query("SELECT u.id FROM UserEntity u WHERE u.role = :role")
	Long getIdByRole(@Param("role")String string);
}
