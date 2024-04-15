package fr.stephanj.app.quizzako.application.error.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import fr.stephanj.app.quizzako.application.error.dto.ErrorDTO;
import fr.stephanj.app.quizzako.application.user.response.BasicUserFullNameResponse;
import fr.stephanj.app.quizzako.domain.user.model.User;
import fr.stephanj.app.quizzako.domain.user.service.UserService;

@ControllerAdvice
public class GlobalErrorHandler {

	@Autowired
	UserService userService;

	@ExceptionHandler
	public String globalHandler(Exception ex, Model model, @AuthenticationPrincipal UserDetails userDetails) {
		if (userDetails != null) {
			User user = userService.getUserByEmail(userDetails.getUsername());
			BasicUserFullNameResponse dto = new BasicUserFullNameResponse(user.getFirstname(), user.getName());
			model.addAttribute("user", dto);
		}
		ErrorDTO dto = new ErrorDTO(ex.getMessage());
		model.addAttribute("error", dto);
		return "error";
	}
}
