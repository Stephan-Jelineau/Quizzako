package fr.stephanj.app.quizzako.presentation.cohort.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.stephanj.app.quizzako.application.cohort.ShowMyCohortsUseCase;
import fr.stephanj.app.quizzako.presentation.cohort.common.CohortConstants;
import fr.stephanj.app.quizzako.presentation.cohort.response.BasicCohortResponse;

@Controller
@RequestMapping(CohortConstants.COHORT_MANAGE_URL)
public class ShowMyCohortsController {

	@Autowired
	ShowMyCohortsUseCase showUseCase;

	@GetMapping
	public String showMyCohorts(Model model, @AuthenticationPrincipal UserDetails userDetail) {
		List<BasicCohortResponse> dto = showUseCase.getMyCohorts(userDetail.getUsername());

		if (dto == null || dto.isEmpty()) {
			model.addAttribute(CohortConstants.FAIL_MESSAGE_ATTR, "You have no cohorts owned yet");
		} else {
			model.addAttribute(CohortConstants.COHORTS_ATTR, dto);
		}

		return CohortConstants.COHORT_MANAGE_PAGE;
	}
}
