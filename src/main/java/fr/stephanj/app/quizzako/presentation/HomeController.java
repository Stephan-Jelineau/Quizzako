package fr.stephanj.app.quizzako.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.stephanj.app.quizzako.application.user.outbound.UserRepository;
import fr.stephanj.app.quizzako.domain.User;
import fr.stephanj.app.quizzako.presentation.user.response.BasicUserFullNameResponse;

@Controller
@RequestMapping(HomeConstants.HOME_URL)
public class HomeController {

	@Autowired
	UserRepository userRepository;

	@GetMapping
	public String home(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		if (userDetails != null) {
			User user = userRepository.getUserByEmail(userDetails.getUsername());
			BasicUserFullNameResponse dto = new BasicUserFullNameResponse(user.getFirstname(), user.getName());
			model.addAttribute("user", dto);
		}
		return "index";
	}
}
