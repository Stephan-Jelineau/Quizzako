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
import fr.stephanj.app.quizzako.presentation.user.request.ViewAndUpdateUserRequest;
import fr.stephanj.app.quizzako.presentation.user.response.BasicUserFullNameResponse;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/account")
public class ViewAndUpdateUserController {

	private static final String VIEW_USER_PAGE = "user/view_user";
	private static final String VIEW_FORM = "user";
	private static final String SUCCESS_FLASH_ATTR = "successMessage";

	@Autowired
	UserViewAndUpdateUseCase useCase;

	@GetMapping
	public String showUserInfos(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		ViewAndUpdateUserRequest request = useCase.getFormUserInfoFor(userDetails.getUsername());
		model.addAttribute(VIEW_FORM, request);
		return VIEW_USER_PAGE;
	}

	@PostMapping
	public ModelAndView updateUser(@AuthenticationPrincipal UserDetails userDetails,
			@Valid @ModelAttribute(VIEW_FORM) ViewAndUpdateUserRequest userRequest, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors())
			return new ModelAndView(VIEW_USER_PAGE);

		BasicUserFullNameResponse dto = useCase.updateUser(userRequest, userDetails.getUsername());
		redirectAttributes.addFlashAttribute(SUCCESS_FLASH_ATTR,
				"User " + dto.getFirstname() + " " + dto.getName() + " updated");
		return new ModelAndView("redirect:/account");
	}

}
