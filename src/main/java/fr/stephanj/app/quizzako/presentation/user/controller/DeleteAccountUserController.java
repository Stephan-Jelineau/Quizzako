package fr.stephanj.app.quizzako.presentation.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.stephanj.app.quizzako.application.user.usecase.UserDeleteAccountUseCase;
import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/account/delete")
@Controller
public class DeleteAccountUserController {

	private static final String SUCCESS_FLASH_ATTR = "successMessage";

	@Autowired
	UserDeleteAccountUseCase useCase;

	@GetMapping
	public String deleteAccount(@AuthenticationPrincipal UserDetails userDetails, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		useCase.deleteAccount(userDetails.getUsername(), request);
		redirectAttributes.addFlashAttribute(SUCCESS_FLASH_ATTR, "Account successfully deleted");
		return "redirect:/";
	}

}
