package fr.stephanj.app.quizzako.infrastructure.requestrole.persistence;

import java.util.List;

import fr.stephanj.app.quizzako.domain.requestrole.model.RequestRole;

public interface RequestRoleRepository {

	void createRequestRole(RequestRole requestRole);

	List<RequestRole> findAllRequest();

	RequestRole findById(Long id);

	void update(RequestRole requestRole);

}
