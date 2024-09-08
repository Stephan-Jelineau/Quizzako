package fr.stephanj.app.quizzako.application.requestrole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.stephanj.app.quizzako.domain.RequestRole;
import fr.stephanj.app.quizzako.domain.repository.RequestRoleRepository;
import fr.stephanj.app.quizzako.presentation.requestrole.request.GrantRoleRequest;

@Component
public class DenyRoleUseCase {

	@Autowired
	RequestRoleRepository requestRoleRepository;

	@Transactional
	public void denyRole(GrantRoleRequest grantRoleRequest) {
		RequestRole rqst = requestRoleRepository.getById(grantRoleRequest.getIdRoleRequest());
		rqst.closeRequest();
		requestRoleRepository.updateRequest(rqst);
	}

}
