package fr.stephanj.app.quizzako.application.requestrole.outbound;

import java.util.List;

import fr.stephanj.app.quizzako.domain.RequestRole;

public interface RequestRoleRepository {

	void saveRequestRole(RequestRole requestRole);

	List<RequestRole> getAllRequest();

	RequestRole getById(Long id);

	void updateRequest(RequestRole requestRole);

	boolean isExistingAndIsActiveFor(Long userId);
}
