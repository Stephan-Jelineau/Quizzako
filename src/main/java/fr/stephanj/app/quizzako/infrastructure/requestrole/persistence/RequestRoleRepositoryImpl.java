package fr.stephanj.app.quizzako.infrastructure.requestrole.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.stephanj.app.quizzako.application.requestrole.exception.RequestRoleAlreadyExistsForUser;
import fr.stephanj.app.quizzako.application.requestrole.exception.RequestRoleNotFoundException;
import fr.stephanj.app.quizzako.application.requestrole.mapper.RequestRoleMapper;
import fr.stephanj.app.quizzako.domain.requestrole.model.RequestRole;
import fr.stephanj.app.quizzako.domain.user.model.User;
import fr.stephanj.app.quizzako.infrastructure.requestrole.entity.RequestRoleEntity;
import fr.stephanj.app.quizzako.infrastructure.user.entity.UserEntity;
import fr.stephanj.app.quizzako.infrastructure.user.persistence.UserRepository;

@Transactional
@Repository
public class RequestRoleRepositoryImpl implements RequestRoleRepository {

	@Autowired
	JpaRequestRoleRepository jpaRequestRepo;

	@Autowired
	UserRepository userRepo;

	@Override
	public void createRequestRole(RequestRole requestRole) {
		Long userId = requestRole.getUser().getId();
		if (jpaRequestRepo.existsByUserIdAndIsActiveTrue(userId))
			throw new RequestRoleAlreadyExistsForUser(
					"A request is already active for your account, waiting for acceptance");

		User user = userRepo.findById(userId);
		RequestRoleEntity entity = new RequestRoleEntity(requestRole, new UserEntity(user));
		jpaRequestRepo.save(entity);
	}

	@Override
	public List<RequestRole> findAllRequest() {
		List<RequestRoleEntity> requests = jpaRequestRepo.findAll();
		if (requests.isEmpty())
			return null;
		return requests.stream().map(r -> RequestRoleMapper.toDomainRequestRole(r)).toList();
	}

	@Override
	public RequestRole findById(Long id) {
		RequestRoleEntity entity = doFindById(id);
		return RequestRoleMapper.toDomainRequestRole(entity);
	}

	private RequestRoleEntity doFindById(Long id) {
		RequestRoleEntity entity = jpaRequestRepo.findById(id).orElseThrow(
				() -> new RequestRoleNotFoundException("The role request with id [" + id + "] was not found"));
		return entity;
	}

	@Override
	public void update(RequestRole requestRole) {
		RequestRoleEntity entity = new RequestRoleEntity(requestRole, new UserEntity(requestRole.getUser()));
		jpaRequestRepo.save(entity);
	}

}
