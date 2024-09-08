package fr.stephanj.app.quizzako.presentation.cohort.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.stephanj.app.quizzako.application.cohort.ShowCohortsGlobalScoreView;
import fr.stephanj.app.quizzako.presentation.cohort.common.CohortConstants;
import fr.stephanj.app.quizzako.presentation.cohort.response.BasicCohortScoreResponse;

@Controller
@RequestMapping(CohortConstants.COHORT_SCORES_URL)
public class ShowScoresCohortsController {

	@Autowired
	ShowCohortsGlobalScoreView globalUseCase;

	@GetMapping
	public String showGlobalScoreView(Model model, @AuthenticationPrincipal UserDetails user) {
		List<BasicCohortScoreResponse> dtos = globalUseCase.getGlobalScoreByOwnedCohort(user.getUsername());

		model.addAttribute(CohortConstants.COHORTS_ATTR, dtos);

		return CohortConstants.COHORT_SCORES_PAGE;
	}
}
