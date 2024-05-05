package fr.stephanj.app.quizzako.presentation.grantrole.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.stephanj.app.quizzako.application.grantrole.usecase.GrantRoleUseCase;
import fr.stephanj.app.quizzako.presentation.grantrole.common.GrantRoleConstants;
import fr.stephanj.app.quizzako.presentation.requestrole.common.RequestRoleConstants;

@Controller
@RequestMapping(GrantRoleConstants.GRANT_REQUEST_URL)
public class GrantRoleRequestController {

	private static final String SUCCESS_MESSAGE_ATTR = "successMessage";
	@Autowired
	GrantRoleUseCase useCase;

	@GetMapping("/{id}")
	public String grantRequestedRole(@PathVariable(value = "id", required = true) Long id, Model model,
			RedirectAttributes redirectAttr) {
		useCase.grantRole(id);
		redirectAttr.addFlashAttribute(SUCCESS_MESSAGE_ATTR, "Request granted succesfully");
		return "redirect:" + RequestRoleConstants.SHOW_REQUESTS_URL;
	}

}
