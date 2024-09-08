package fr.stephanj.app.quizzako.presentation.dashboard.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.stephanj.app.quizzako.presentation.dashboard.common.DashboardConstants;

@RequestMapping(DashboardConstants.DASHBOARD_URL)
@Controller
public class ShowDashboardController {

	@PreAuthorize("hasAnyRole('TEACHER', 'STUDENT')")
	@GetMapping
	public String showDashboard() {
		return DashboardConstants.DASHBOARD_PAGE;
	}
}
