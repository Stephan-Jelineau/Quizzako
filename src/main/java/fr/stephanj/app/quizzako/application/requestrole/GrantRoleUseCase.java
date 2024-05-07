package fr.stephanj.app.quizzako.application.requestrole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.stephanj.app.quizzako.application.requestrole.outbound.RequestRoleRepository;
import fr.stephanj.app.quizzako.application.user.outbound.UserRepository;
import fr.stephanj.app.quizzako.domain.RequestRole;
import fr.stephanj.app.quizzako.domain.User;
import fr.stephanj.app.quizzako.presentation.requestrole.request.GrantRoleRequest;

@Component
public class GrantRoleUseCase {

	@Autowired
	RequestRoleRepository requestRoleRepository;

	@Autowired
	UserRepository userRepository;

	@Transactional
	public void grantRole(GrantRoleRequest grantRoleRequest) {
		RequestRole rqst = requestRoleRepository.getById(grantRoleRequest.getIdRoleRequest());
		User user = rqst.getUser();
		user.updateRole(rqst.getRoleResquested());
		userRepository.updateUserWithoutNewMail(user);
		rqst.closeRequest();
		requestRoleRepository.updateRequest(rqst);
	}

}
