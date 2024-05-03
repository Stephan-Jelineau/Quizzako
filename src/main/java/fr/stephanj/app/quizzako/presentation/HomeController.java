package fr.stephanj.app.quizzako.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.stephanj.app.quizzako.application.user.service.UserService;
import fr.stephanj.app.quizzako.domain.user.model.User;
import fr.stephanj.app.quizzako.presentation.user.response.BasicUserFullNameResponse;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	UserService userService;

	@GetMapping
	public String home(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		if (userDetails != null) {
			User user = userService.getUserByEmail(userDetails.getUsername());
			BasicUserFullNameResponse dto = new BasicUserFullNameResponse(user.getFirstname(), user.getName());
			model.addAttribute("user", dto);
		}
		return "index";
	}
}
