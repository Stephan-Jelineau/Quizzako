package fr.stephanj.app.quizzako.application.requestrole.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.stephanj.app.quizzako.domain.requestrole.model.RequestRole;
import fr.stephanj.app.quizzako.infrastructure.requestrole.persistence.RequestRoleRepository;

@Service
public class RequestRoleServiceImpl implements RequestRoleService {

	@Autowired
	RequestRoleRepository roleRepo;

	@Override
	public void saveRequestRole(RequestRole requestRole) {
		roleRepo.createRequestRole(requestRole);
	}
}
