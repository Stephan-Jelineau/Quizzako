package fr.stephanj.app.quizzako.application.requestrole.service;

import java.util.List;

import fr.stephanj.app.quizzako.domain.requestrole.model.RequestRole;

public interface RequestRoleService {
	void saveRequestRole(RequestRole requestRole);

	List<RequestRole> getAllRequest();
}
