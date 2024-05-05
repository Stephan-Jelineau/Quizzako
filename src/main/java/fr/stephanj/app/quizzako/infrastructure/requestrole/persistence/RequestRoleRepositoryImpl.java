package fr.stephanj.app.quizzako.infrastructure.requestrole.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.stephanj.app.quizzako.application.requestrole.exception.RequestRoleAlreadyExistsForUser;
import fr.stephanj.app.quizzako.application.requestrole.mapper.RequestRoleMapper;
import fr.stephanj.app.quizzako.application.user.exception.UserNotFoundException;
import fr.stephanj.app.quizzako.domain.requestrole.model.RequestRole;
import fr.stephanj.app.quizzako.infrastructure.requestrole.entity.RequestRoleEntity;
import fr.stephanj.app.quizzako.infrastructure.user.entity.UserEntity;
import fr.stephanj.app.quizzako.infrastructure.user.persistence.JpaUserRepository;

@Transactional
@Repository
public class RequestRoleRepositoryImpl implements RequestRoleRepository {

	@Autowired
	JpaRequestRoleRepository jpaRequestRepo;

	@Autowired
	JpaUserRepository jpaUserRepo;

	@Override
	public void createRequestRole(RequestRole requestRole) {
		Long userId = requestRole.getUser().getId();
		if (jpaRequestRepo.existsByUserId(userId))
			throw new RequestRoleAlreadyExistsForUser(
					"A request is already active for your account, waiting for acceptance");
		UserEntity user = jpaUserRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found, cannot create request"));
		RequestRoleEntity entity = new RequestRoleEntity(requestRole, user);
		jpaRequestRepo.save(entity);
	}

	@Override
	public List<RequestRole> findAllRequest() {
		List<RequestRoleEntity> requests = jpaRequestRepo.findAll();
		if (requests.isEmpty())
			return null;
		return requests.stream().map(r -> RequestRoleMapper.toDomainRequestRole(r)).toList();
	}

}
