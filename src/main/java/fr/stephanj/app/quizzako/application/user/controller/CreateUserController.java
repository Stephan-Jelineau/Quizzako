
package fr.stephanj.app.quizzako.application.user.controller;

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

import fr.stephanj.app.quizzako.application.user.request.CreateUserRequest;
import fr.stephanj.app.quizzako.application.user.usecase.UserRegistrationUseCase;
import fr.stephanj.app.quizzako.domain.user.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/register")
public class CreateUserController {

	private static final String USER_REGISTER_PAGE = "user/register";
	private static final String MODEL_ATTR_FORM = "userForm";
	private static final String ERROR_CODE_PASSWORD_MUST_MATCH = "user.passwordsMustMatch";

	@Autowired
	private UserRegistrationUseCase userRegistration;

	@GetMapping
	public String customerForm(Model model) {
		model.addAttribute(MODEL_ATTR_FORM, new CreateUserRequest());
		return USER_REGISTER_PAGE;
	}

	@PostMapping
	public ModelAndView createUser(@Valid @ModelAttribute(MODEL_ATTR_FORM) CreateUserRequest userRequest,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletResponse response) {

		if (bindingResult.hasErrors())
			return new ModelAndView(USER_REGISTER_PAGE);

		if (!userRequest.getPassword1().equals(userRequest.getPassword2())) {
			bindingResult.rejectValue("password2", ERROR_CODE_PASSWORD_MUST_MATCH,
					"Passwords must match, please retry");
			return new ModelAndView(USER_REGISTER_PAGE);
		}

		User user = userRegistration.registerNewUserAndConnect(userRequest, request, response);
		redirectAttributes.addFlashAttribute("successMessage",
				"User " + user.getFirstname() + " " + user.getName() + " created");
		return new ModelAndView("redirect:/");

	}

}
