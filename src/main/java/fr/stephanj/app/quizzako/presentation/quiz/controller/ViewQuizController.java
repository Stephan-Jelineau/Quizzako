package fr.stephanj.app.quizzako.presentation.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.stephanj.app.quizzako.application.quiz.StartQuizUseCase;
import fr.stephanj.app.quizzako.presentation.quiz.common.QuizConstants;
import fr.stephanj.app.quizzako.presentation.quiz.request.QuizFormRequest;

@RequestMapping(QuizConstants.QUIZ_URL)
@Controller
public class ViewQuizController {

	@Autowired
	StartQuizUseCase useCase;

	@GetMapping
	public String startQuiz(@RequestParam(value = "id", required = true) Long id, Model model) {

		if (!model.containsAttribute(QuizConstants.QUIZ_FORM)) {
			QuizFormRequest quiz = useCase.getQuizSelected(id);
			model.addAttribute(QuizConstants.QUIZ_FORM, quiz);
		}

		return QuizConstants.QUIZ_PAGE;
	}
}
