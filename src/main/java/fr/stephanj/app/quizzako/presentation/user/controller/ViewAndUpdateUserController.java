package fr.stephanj.app.quizzako.presentation.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.stephanj.app.quizzako.application.user.usecase.UserViewAndUpdateUseCase;
import fr.stephanj.app.quizzako.presentation.user.controller.common.UserConstants;
import fr.stephanj.app.quizzako.presentation.user.request.ViewAndUpdateUserRequest;
import fr.stephanj.app.quizzako.presentation.user.response.BasicUserFullNameResponse;
import jakarta.validation.Valid;

@Controller
@RequestMapping(UserConstants.USER_ACCOUNT_URL)
public class ViewAndUpdateUserController {

	private static final String VIEW_FORM = "user";

	@Autowired
	UserViewAndUpdateUseCase useCase;

	@GetMapping
	public String showUserInfos(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		ViewAndUpdateUserRequest request = useCase.getFormUserInfoFor(userDetails.getUsername());
		model.addAttribute(VIEW_FORM, request);
		return UserConstants.VIEW_USER_PAGE;
	}

	@PostMapping
	public ModelAndView updateUser(@AuthenticationPrincipal UserDetails userDetails,
			@Valid @ModelAttribute(VIEW_FORM) ViewAndUpdateUserRequest userRequest, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors())
			return new ModelAndView(UserConstants.VIEW_USER_PAGE);

		BasicUserFullNameResponse dto = useCase.updateUserByUpdateView(userRequest, userDetails.getUsername());
		redirectAttributes.addFlashAttribute(UserConstants.SUCCESS_FLASH_ATTR,
				"User " + dto.getFirstname() + " " + dto.getName() + " updated");
		return new ModelAndView("redirect:" + UserConstants.USER_ACCOUNT_URL);
	}

}
