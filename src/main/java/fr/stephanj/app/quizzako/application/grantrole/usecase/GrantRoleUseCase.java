package fr.stephanj.app.quizzako.application.grantrole.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.stephanj.app.quizzako.application.requestrole.service.RequestRoleService;
import fr.stephanj.app.quizzako.application.user.service.UserService;
import fr.stephanj.app.quizzako.domain.requestrole.model.RequestRole;
import fr.stephanj.app.quizzako.domain.user.model.User;

@Component
public class GrantRoleUseCase {

	@Autowired
	RequestRoleService rqstService;

	@Autowired
	UserService userService;

	@Transactional
	public void grantRole(Long id) {
		RequestRole rqst = rqstService.getById(id);
		User user = userService.getUserByEmail(rqst.getUser().getEmail());
		user.updateRole(rqst.getRoleResquested());
		userService.updateUser(user);
		rqst.closeRequest();
		rqstService.updateRequest(rqst);
	}

}
