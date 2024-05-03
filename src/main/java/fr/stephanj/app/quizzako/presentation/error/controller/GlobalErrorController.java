package fr.stephanj.app.quizzako.presentation.error.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import fr.stephanj.app.quizzako.application.user.service.UserService;
import fr.stephanj.app.quizzako.domain.user.model.User;
import fr.stephanj.app.quizzako.presentation.error.response.BasicErrorMessageResponse;
import fr.stephanj.app.quizzako.presentation.user.response.BasicUserFullNameResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalErrorController {

	@Autowired
	UserService userService;

	@ExceptionHandler
	public String globalHandler(HttpSession session, Exception ex, Model model, @AuthenticationPrincipal UserDetails userDetails,
			HttpServletRequest request) {

		if (userDetails == null) {
			BasicErrorMessageResponse dto = new BasicErrorMessageResponse(ex.getMessage());
			model.addAttribute("error", dto);
			return "error";
		}

		try {
			User user = userService.getUserByEmail(userDetails.getUsername());
			BasicUserFullNameResponse userDto = new BasicUserFullNameResponse(user.getFirstname(), user.getName());
			BasicErrorMessageResponse errorDto = new BasicErrorMessageResponse(ex.getMessage());
			model.addAttribute("user", userDto);
			model.addAttribute("error", errorDto);
		} catch (Exception e) {
			new SecurityContextLogoutHandler().logout(request, null, null);
			BasicErrorMessageResponse dto = new BasicErrorMessageResponse("You have been disconnected\n" + e.getMessage());
			model.addAttribute("error", dto);
		}
		return "error";
	}
}
