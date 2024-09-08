package fr.stephanj.app.quizzako.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(HomeConstants.HOME_URL)
public class HomeController {

	@GetMapping
	public String home() {
		return "index";
	}
}
