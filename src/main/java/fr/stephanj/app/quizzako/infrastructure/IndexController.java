package fr.stephanj.app.quizzako.infrastructure;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController {

	@GetMapping("/")
	public ModelAndView homePage() {
		return new ModelAndView("index");
	}

}
