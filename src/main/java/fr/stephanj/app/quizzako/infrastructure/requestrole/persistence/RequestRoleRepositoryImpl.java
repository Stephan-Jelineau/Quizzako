package fr.stephanj.app.quizzako.infrastructure.requestrole.persistence;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import fr.stephanj.app.quizzako.application.requestrole.outbound.RequestRoleRepository;
import fr.stephanj.app.quizzako.domain.RequestRole;
import fr.stephanj.app.quizzako.domain.exception.requestrole.RequestRoleAlreadyExistsForUser;
import fr.stephanj.app.quizzako.domain.exception.requestrole.RequestRoleNotFoundException;
import fr.stephanj.app.quizzako.infrastructure.requestrole.entity.RequestRoleEntity;
import fr.stephanj.app.quizzako.infrastructure.requestrole.mapper.RequestRoleEntityMapper;

@Transactional
public class RequestRoleRepositoryImpl implements RequestRoleRepository {

	@Autowired
	JpaRequestRoleRepository jpaRequestRepo;

	@Override
	public void saveRequestRole(RequestRole requestRole) {
		if (isExistingAndIsActiveFor(requestRole.getUser().getId()))
			throw new RequestRoleAlreadyExistsForUser(
					"A request is already active for your account, waiting for acceptance");
		jpaRequestRepo.save(RequestRoleEntityMapper.toEntity(requestRole));
	}

	@Override
	public List<RequestRole> getAllRequest() {
		return jpaRequestRepo.findAll().stream().map(RequestRoleEntityMapper::toDomain).collect(Collectors.toList());
	}

	@Override
	public RequestRole getById(Long id) {
		RequestRoleEntity entity = jpaRequestRepo.findById(id).orElseThrow(
				() -> new RequestRoleNotFoundException("The role request with id [" + id + "] was not found"));
		return RequestRoleEntityMapper.toDomain(entity);
	}

	@Override
	public void updateRequest(RequestRole requestRole) {
		jpaRequestRepo.save(RequestRoleEntityMapper.toEntity(requestRole));
	}

	@Override
	public boolean isExistingAndIsActiveFor(Long userId) {
		return jpaRequestRepo.existsByUserIdAndIsActiveTrue(userId);
	}

}
