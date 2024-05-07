package fr.stephanj.app.quizzako.presentation.error.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import fr.stephanj.app.quizzako.application.user.outbound.UserRepository;
import fr.stephanj.app.quizzako.domain.Role;
import fr.stephanj.app.quizzako.domain.User;
import fr.stephanj.app.quizzako.presentation.error.common.ErrorConstants;
import fr.stephanj.app.quizzako.presentation.error.response.BasicErrorMessageResponse;
import fr.stephanj.app.quizzako.presentation.user.response.BasicUserFullNameResponse;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalErrorController {

	private static final String USER_RESPONSE_ATTR = "user";
	private static final String ERROR_RESPONSE_ATTR = "error";

	@Autowired
	UserRepository userRepository;

	@ExceptionHandler
	public String globalHandler(Exception ex, Model model, @AuthenticationPrincipal UserDetails userDetails,
			HttpServletRequest request) {

		StringBuilder message = new StringBuilder();
		concatenateExceptionMessagesHelper(ex, message);

		if (userDetails == null) {
			BasicErrorMessageResponse dto = new BasicErrorMessageResponse(message.toString());
			model.addAttribute(ERROR_RESPONSE_ATTR, dto);
			return ErrorConstants.ERROR_USER_PAGE;
		}

		try {
			User user = userRepository.getUserByEmail(userDetails.getUsername());
			if (user.getRole().equals(Role.ADMIN)) {
				BasicErrorMessageResponse errorDto = new BasicErrorMessageResponse(message.toString());
				model.addAttribute(ERROR_RESPONSE_ATTR, errorDto);
				return ErrorConstants.ERROR_ADMIN_PAGE;
			}
			BasicUserFullNameResponse userDto = new BasicUserFullNameResponse(user.getFirstname(), user.getName());
			BasicErrorMessageResponse errorDto = new BasicErrorMessageResponse(message.toString());
			model.addAttribute(USER_RESPONSE_ATTR, userDto);
			model.addAttribute(ERROR_RESPONSE_ATTR, errorDto);
		} catch (Exception e) {
			new SecurityContextLogoutHandler().logout(request, null, null);
			BasicErrorMessageResponse dto = new BasicErrorMessageResponse(
					"You have been disconnected\n" + message.toString());
			model.addAttribute(ERROR_RESPONSE_ATTR, dto);
		}
		return ErrorConstants.ERROR_USER_PAGE;
	}

	private void concatenateExceptionMessagesHelper(Throwable exception, StringBuilder stringBuilder) {
		if (exception != null) {
			stringBuilder.append(exception.getMessage()).append("\n");
			Throwable cause = exception.getCause();
			if (cause != null && !cause.getMessage().equals(exception.getMessage())) {
				concatenateExceptionMessagesHelper(cause, stringBuilder);
			}
		}
	}
}
