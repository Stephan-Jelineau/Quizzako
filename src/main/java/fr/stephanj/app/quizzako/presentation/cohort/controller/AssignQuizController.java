package fr.stephanj.app.quizzako.presentation.cohort.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.stephanj.app.quizzako.application.cohort.AssignQuizUseCase;
import fr.stephanj.app.quizzako.application.quiz.ShowQuizzesToAssignUseCase;
import fr.stephanj.app.quizzako.presentation.cohort.common.CohortConstants;
import fr.stephanj.app.quizzako.presentation.cohort.response.CohortQuizReponse;
import fr.stephanj.app.quizzako.presentation.dashboard.common.DashboardConstants;
import fr.stephanj.app.quizzako.presentation.quiz.common.QuizConstants;

@Controller
@RequestMapping(CohortConstants.COHORT_ASSIGN_QUIZ_URL)
public class AssignQuizController {

	@Autowired
	ShowQuizzesToAssignUseCase showUseCase;

	@Autowired
	AssignQuizUseCase assignUseCase;

	@GetMapping
	public String getQuizzesViewToAssign(@RequestParam(value = "id", required = true) Long cohortId, Model model,
			@AuthenticationPrincipal UserDetails user) {

		CohortQuizReponse dto = showUseCase.getQuizzesByUserNameForCohort(user.getUsername(), cohortId);

		if (dto.getQuizzes() == null || dto.getQuizzes().isEmpty()) {
			model.addAttribute(QuizConstants.FAIL_MESSAGE_ATTR, "No quiz created yet , nothing to assign");
			return "redirect:" + DashboardConstants.DASHBOARD_URL;
		}

		model.addAttribute(CohortConstants.COHORT_QUIZ_ATTR, dto);

		return CohortConstants.COHORT_ASSIGN_QUIZ_PAGE;
	}

	@PostMapping
	public String sendAssignedQuizzes(@ModelAttribute CohortQuizReponse dto,
			@RequestParam(value = "id", required = true) Long cohortId, Model model,
			@AuthenticationPrincipal UserDetails user, RedirectAttributes redirectAttributes) {

		assignUseCase.assignQuizzesToCohort(dto.getIds(), cohortId);
		redirectAttributes.addAttribute("id", cohortId);
		redirectAttributes.addFlashAttribute(CohortConstants.SUCCESS_MESSAGE_ATTR,
				"Quizzes assigned updated successfully");

		return "redirect:/" + CohortConstants.COHORT_ASSIGN_QUIZ_URL;
	}
}
