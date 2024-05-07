
package fr.stephanj.app.quizzako.presentation.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.stephanj.app.quizzako.application.user.UserSignUpUseCase;
import fr.stephanj.app.quizzako.presentation.HomeConstants;
import fr.stephanj.app.quizzako.presentation.user.common.UserConstants;
import fr.stephanj.app.quizzako.presentation.user.request.CreateUserRequest;
import fr.stephanj.app.quizzako.presentation.user.response.BasicUserFullNameResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
@RequestMapping(UserConstants.USER_REGISTER_URL)
public class CreateUserController {

	private static final String REGISTER_FORM = "userForm";
	
	@Autowired
	private UserSignUpUseCase userRegistration;

	@GetMapping
	public String customerForm(Model model) {
		model.addAttribute(REGISTER_FORM, new CreateUserRequest());
		return UserConstants.USER_REGISTER_PAGE;
	}

	@PostMapping
	public ModelAndView createUser(@Valid @ModelAttribute(REGISTER_FORM) CreateUserRequest userRequest,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletResponse response) {

		if (bindingResult.hasErrors())
			return new ModelAndView(UserConstants.USER_REGISTER_PAGE);

		if (!userRequest.getPassword1().equals(userRequest.getPassword2())) {
			bindingResult.rejectValue("password2", UserConstants.ERROR_CODE_PASSWORD_MUST_MATCH,
					"Passwords must match, please retry");
			return new ModelAndView(UserConstants.USER_REGISTER_PAGE);
		}

		BasicUserFullNameResponse dto = userRegistration.registerNewUserAndConnect(userRequest, request, response);
		redirectAttributes.addFlashAttribute(UserConstants.SUCCESS_FLASH_ATTR,
				"User " + dto.getFirstname() + " " + dto.getName() + " created");
		return new ModelAndView("redirect:" + HomeConstants.HOME_URL);

	}

}
