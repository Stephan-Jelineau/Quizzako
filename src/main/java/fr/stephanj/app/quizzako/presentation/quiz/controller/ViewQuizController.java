package fr.stephanj.app.quizzako.presentation.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.stephanj.app.quizzako.application.quiz.StartQuizUseCase;
import fr.stephanj.app.quizzako.presentation.quiz.common.QuizConstants;
import fr.stephanj.app.quizzako.presentation.quiz.request.QuizFormRequest;

@RequestMapping(QuizConstants.QUIZ_URL)
@Controller
public class ViewQuizController {

	private static final String NOT_ALLOWED_QUIZ = "Not allowed quiz";
	private static final String FAIL_MESSAGE = "failMessage";

	@Autowired
	StartQuizUseCase useCase;

	@GetMapping
	public String startQuiz(@RequestParam(value = "id", required = true) Long id, Model model, RedirectAttributes redirectAttribute) {

		boolean isQuizPublic = useCase.isQuizPublic(id);
		
		if (!isQuizPublic) {
			redirectAttribute.addFlashAttribute(FAIL_MESSAGE, NOT_ALLOWED_QUIZ);
			return "redirect:" + QuizConstants.QUIZZES_URL;
		}
		
		if (!model.containsAttribute(QuizConstants.QUIZ_ATTR)) {
			QuizFormRequest quiz = useCase.getQuizSelected(id);
			model.addAttribute(QuizConstants.QUIZ_ATTR, quiz);
		}

		return QuizConstants.QUIZ_PAGE;
	}
}
