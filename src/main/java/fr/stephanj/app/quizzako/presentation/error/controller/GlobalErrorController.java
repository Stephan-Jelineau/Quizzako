package fr.stephanj.app.quizzako.presentation.error.controller;

import java.util.Set;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import fr.stephanj.app.quizzako.presentation.error.common.ErrorConstants;
import fr.stephanj.app.quizzako.presentation.error.response.BasicErrorMessageResponse;

@ControllerAdvice
public class GlobalErrorController {

	private static final String ERROR_RESPONSE_ATTR = "error";

	@ExceptionHandler(Exception.class)
	public String errorAdminHandler(@AuthenticationPrincipal UserDetails userDetails, Exception ex, Model model) {

		StringBuilder message = new StringBuilder();
		concatenateExceptionMessagesHelper(ex, message);
		BasicErrorMessageResponse errorDto = new BasicErrorMessageResponse(message.toString());
		model.addAttribute(ERROR_RESPONSE_ATTR, errorDto);

		Set<String> roles = AuthorityUtils.authorityListToSet(userDetails.getAuthorities());

		if (roles.contains("ROLE_ADMIN"))
			return ErrorConstants.ERROR_ADMIN_PAGE;

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
