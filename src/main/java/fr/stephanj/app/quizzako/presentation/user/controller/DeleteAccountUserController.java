package fr.stephanj.app.quizzako.presentation.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.stephanj.app.quizzako.application.user.UserDeleteAccountUseCase;
import fr.stephanj.app.quizzako.presentation.HomeConstants;
import fr.stephanj.app.quizzako.presentation.user.common.UserConstants;
import jakarta.servlet.http.HttpServletRequest;

@RequestMapping(UserConstants.USER_DELETE_URL)
@Controller
public class DeleteAccountUserController {

	@Autowired
	UserDeleteAccountUseCase useCase;

	@GetMapping
	public String deleteAccount(@AuthenticationPrincipal UserDetails userDetails, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		useCase.deleteAccount(userDetails.getUsername(), request);
		redirectAttributes.addFlashAttribute(UserConstants.SUCCESS_FLASH_ATTR, "Account successfully deleted");
		return "redirect:" + HomeConstants.HOME_URL;
	}

}
