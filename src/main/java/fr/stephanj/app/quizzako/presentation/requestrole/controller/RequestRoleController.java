package fr.stephanj.app.quizzako.presentation.requestrole.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.stephanj.app.quizzako.application.requestrole.usecase.SendRequestRoleUseCase;
import fr.stephanj.app.quizzako.presentation.HomeConstants;
import fr.stephanj.app.quizzako.presentation.requestrole.common.RequestRoleConstants;
import fr.stephanj.app.quizzako.presentation.user.controller.common.UserConstants;

@RequestMapping(RequestRoleConstants.REQUEST_ROLE_URL)
@Controller
public class RequestRoleController {

	@Autowired
	SendRequestRoleUseCase useCase;

	@GetMapping
	public String requestRole(@RequestParam("role") String role, RedirectAttributes redirectAttributes,
			@AuthenticationPrincipal UserDetails userDetails) {

		useCase.requestRoleForUser(userDetails.getUsername(), role.toUpperCase());
		redirectAttributes.addFlashAttribute(UserConstants.SUCCESS_FLASH_ATTR,
				"Request successfully sent, waiting for approval");

		return "redirect:" + HomeConstants.HOME_URL;
	}

}
