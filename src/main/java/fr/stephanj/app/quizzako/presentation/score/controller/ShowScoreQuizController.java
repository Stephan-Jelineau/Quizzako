package fr.stephanj.app.quizzako.presentation.score.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.stephanj.app.quizzako.application.score.ShowQuizScoreUseCase;
import fr.stephanj.app.quizzako.presentation.quiz.common.QuizConstants;
import fr.stephanj.app.quizzako.presentation.quiz.request.QuizFormRequest;
import fr.stephanj.app.quizzako.presentation.score.common.ScoreConstants;
import fr.stephanj.app.quizzako.presentation.score.response.ScoreQuizResponse;

@RequestMapping(ScoreConstants.SCORE_QUIZ_URL)
@Controller
public class ShowScoreQuizController {

	@Autowired
	ShowQuizScoreUseCase useCase;

	@GetMapping
	public String showScoreForQuiz(RedirectAttributes redirectAttributes, Model model) {

		if (!model.containsAttribute(QuizConstants.QUIZ_ATTR))
			return "redirect:" + QuizConstants.QUIZZES_URL;

		ScoreQuizResponse res = useCase
				.getScoreOfQuiz((QuizFormRequest) model.getAttribute(QuizConstants.QUIZ_ATTR));
		model.addAttribute(ScoreConstants.SCORE_ATTR, res);

		return ScoreConstants.SCORE_QUIZ_PAGE;
	}
}
