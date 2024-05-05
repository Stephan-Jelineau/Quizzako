package fr.stephanj.app.quizzako.application.requestrole.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.stephanj.app.quizzako.application.requestrole.service.RequestRoleService;
import fr.stephanj.app.quizzako.domain.requestrole.model.RequestRole;

@Component
public class ShowRequestsRoleUseCase {
	
	@Autowired
	RequestRoleService requestService;

	public List<RequestRole> getAllRoleRequest() {
		List<RequestRole> requests = requestService.getAllRequest();
		return requests;
	}
}
