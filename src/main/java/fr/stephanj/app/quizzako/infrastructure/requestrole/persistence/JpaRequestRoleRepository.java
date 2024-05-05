package fr.stephanj.app.quizzako.infrastructure.requestrole.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.stephanj.app.quizzako.infrastructure.requestrole.entity.RequestRoleEntity;

public interface JpaRequestRoleRepository extends JpaRepository<RequestRoleEntity, Long> {
	boolean existsByUserId(Long id);
}
