package fr.stephanj.app.quizzako.presentation.requestrole.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.stephanj.app.quizzako.application.requestrole.usecase.ShowRequestsRoleUseCase;
import fr.stephanj.app.quizzako.domain.requestrole.model.RequestRole;
import fr.stephanj.app.quizzako.presentation.admin.controller.common.AdminConstants;
import fr.stephanj.app.quizzako.presentation.requestrole.common.RequestRoleConstants;

@Controller
@RequestMapping(RequestRoleConstants.SHOW_REQUESTS_URL)
public class ShowRoleRequestsController {

	private static final String FLASH_MESSAGE = "flashMessage";

	private static final String REQUESTS_LIST_ATTR = "requests";

	@Autowired
	ShowRequestsRoleUseCase useCase;

	@GetMapping
	public String getAllRequests(Model model, RedirectAttributes redirectAttr) {
		List<RequestRole> requests = useCase.getAllRoleRequest();

		if (requests == null || requests.isEmpty()) {
			redirectAttr.addFlashAttribute(FLASH_MESSAGE, "No pending role requests");
			return "redirect:" + AdminConstants.ADMIN_HOME_URL;
		}

		model.addAttribute(REQUESTS_LIST_ATTR, requests);
		return RequestRoleConstants.SHOW_REQUESTS_PAGE;
	}
}
