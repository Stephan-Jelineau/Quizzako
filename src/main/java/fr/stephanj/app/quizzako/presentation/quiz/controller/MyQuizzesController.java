package fr.stephanj.app.quizzako.presentation.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.stephanj.app.quizzako.application.quiz.ShowQuizzesOwnedUseCase;
import fr.stephanj.app.quizzako.presentation.quiz.common.QuizConstants;
import fr.stephanj.app.quizzako.presentation.quiz.response.QuizWithNumberOfQuestionResponse;

@RequestMapping(QuizConstants.MY_QUIZZES_URL)
@Controller
public class MyQuizzesController {

	@Autowired
	ShowQuizzesOwnedUseCase useCase;

	@GetMapping
	public String manageMyQuizzes(@AuthenticationPrincipal UserDetails userDetails, Model model) {

		List<QuizWithNumberOfQuestionResponse> quizzes = useCase.getQuizzesByUserName(userDetails.getUsername());

		if (quizzes == null || quizzes.isEmpty()) {
			model.addAttribute(QuizConstants.FAIL_MESSAGE_ATTR, "No quiz created yet");
		} else {
			model.addAttribute(QuizConstants.QUIZZES_ATTR, quizzes);
		}

		return QuizConstants.MY_QUIZZES_PAGE;
	}

}
