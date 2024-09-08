package fr.stephanj.app.quizzako.presentation.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.stephanj.app.quizzako.application.quiz.ShowRandomQuizzesUseCase;
import fr.stephanj.app.quizzako.presentation.quiz.common.QuizConstants;
import fr.stephanj.app.quizzako.presentation.quiz.response.BasicQuizResponse;

@RequestMapping(QuizConstants.QUIZZES_URL)
@Controller
public class ShowRandomQuizzesController {

	@Autowired
	private ShowRandomQuizzesUseCase quizzesUseCase;

	@GetMapping
	public String showRandomQuizzes(Model model) {

		List<BasicQuizResponse> quizzes = quizzesUseCase.getRandomQuizzes(12);
		model.addAttribute("quizzes", quizzes);

		return QuizConstants.QUIZZES_PAGE;
	}

}
