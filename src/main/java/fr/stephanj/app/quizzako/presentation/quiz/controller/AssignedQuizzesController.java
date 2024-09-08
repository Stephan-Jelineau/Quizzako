package fr.stephanj.app.quizzako.presentation.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.stephanj.app.quizzako.application.quiz.ShowAssignedQuizzesUseCase;
import fr.stephanj.app.quizzako.presentation.quiz.common.QuizConstants;
import fr.stephanj.app.quizzako.presentation.score.response.ScoreWithCohortAndDetailsResponse;

@Controller
@RequestMapping(QuizConstants.ASSIGNED_QUIZ_URL)
public class AssignedQuizzesController {

	@Autowired
	ShowAssignedQuizzesUseCase assignedUseCase;

	@GetMapping
	public String showAssignedQuizzes(@AuthenticationPrincipal UserDetails user, Model model) {

		List<ScoreWithCohortAndDetailsResponse> dtos = assignedUseCase.getQuizzesAssigned(user.getUsername());

		model.addAttribute(QuizConstants.QUIZZES_ATTR, dtos);

		return QuizConstants.ASSIGNED_QUIZ_PAGE;
	}
}
