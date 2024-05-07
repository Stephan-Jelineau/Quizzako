package fr.stephanj.app.quizzako.application.requestrole;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.stephanj.app.quizzako.application.requestrole.outbound.RequestRoleRepository;
import fr.stephanj.app.quizzako.domain.RequestRole;
import fr.stephanj.app.quizzako.presentation.requestrole.response.ShowRoleRequestsResponse;

@Component
public class ShowRequestsRoleUseCase {

	@Autowired
	RequestRoleRepository requestRoleRepository;

	public List<ShowRoleRequestsResponse> getAllRoleRequest() {
		List<RequestRole> requests = requestRoleRepository.getAllRequest();
		if (requests == null || requests.isEmpty())
			return null;
		List<ShowRoleRequestsResponse> dtos = new ArrayList<>();
		requests.forEach(r -> {
			ShowRoleRequestsResponse dto = new ShowRoleRequestsResponse(r.getId(), r.isActive(),
					r.getUser().shortDisplay(), r.getOpenDate().toString(),
					r.getCloseDate() == null ? null : r.getCloseDate().toString());
			dtos.add(dto);
		});
		return dtos;
	}
}
