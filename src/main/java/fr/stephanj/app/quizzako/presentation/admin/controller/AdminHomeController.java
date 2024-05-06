package fr.stephanj.app.quizzako.presentation.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.stephanj.app.quizzako.presentation.admin.controller.common.AdminConstants;

@Controller
@RequestMapping(AdminConstants.ADMIN_HOME_URL)
public class AdminHomeController {

	@GetMapping
	public String adminHome() {
		return AdminConstants.ADMIN_HOME_PAGE;
	}
}
