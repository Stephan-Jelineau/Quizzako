package fr.stephanj.app.quizzako.presentation.requestrole.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.stephanj.app.quizzako.application.requestrole.GrantRoleUseCase;
import fr.stephanj.app.quizzako.application.requestrole.ShowRequestsRoleUseCase;
import fr.stephanj.app.quizzako.presentation.admin.controller.common.AdminConstants;
import fr.stephanj.app.quizzako.presentation.requestrole.common.RequestRoleConstants;
import fr.stephanj.app.quizzako.presentation.requestrole.request.GrantRoleRequest;
import fr.stephanj.app.quizzako.presentation.requestrole.response.ShowRoleRequestsResponse;
import jakarta.validation.Valid;

@Controller
@RequestMapping(RequestRoleConstants.SHOW_REQUESTS_URL)
public class ShowAndGrantRoleRequestsController {

	private static final String FLASH_MESSAGE_ATTR = "flashMessage";
	private static final String REQUESTS_LIST_ATTR = "requests";
	private static final String SUCCESS_MESSAGE_ATTR = "successMessage";
	private static final String GRANT_ROLE_ATTR = "grantRole";

	@Autowired
	ShowRequestsRoleUseCase showRequestsRoleUseCase;

	@Autowired
	GrantRoleUseCase grantRoleUseCase;

	@GetMapping
	public String getAllRequests(Model model, RedirectAttributes redirectAttr) {
		List<ShowRoleRequestsResponse> requests = showRequestsRoleUseCase.getAllRoleRequest();

		if (requests == null || requests.isEmpty()) {
			redirectAttr.addFlashAttribute(FLASH_MESSAGE_ATTR, "No pending role requests");
			return "redirect:" + AdminConstants.ADMIN_HOME_URL;
		}

		model.addAttribute(REQUESTS_LIST_ATTR, requests);
		model.addAttribute(GRANT_ROLE_ATTR, new GrantRoleRequest());
		return RequestRoleConstants.SHOW_REQUESTS_PAGE;
	}

	@PostMapping
	public String grantRequestedRole(@Valid @ModelAttribute(GRANT_ROLE_ATTR) GrantRoleRequest grantRoleRequest,
			RedirectAttributes redirectAttr) {
		grantRoleUseCase.grantRole(grantRoleRequest);
		redirectAttr.addFlashAttribute(SUCCESS_MESSAGE_ATTR, "Request granted succesfully");
		return "redirect:" + RequestRoleConstants.SHOW_REQUESTS_URL;
	}
}
