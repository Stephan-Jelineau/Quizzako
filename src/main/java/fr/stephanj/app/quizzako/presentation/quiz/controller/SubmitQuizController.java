package fr.stephanj.app.quizzako.presentation.quiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.stephanj.app.quizzako.presentation.quiz.common.QuizConstants;
import fr.stephanj.app.quizzako.presentation.quiz.request.QuizFormRequest;
import fr.stephanj.app.quizzako.presentation.score.common.ScoreConstants;
import jakarta.validation.Valid;

@RequestMapping(QuizConstants.SUBMIT_QUIZ_URL)
@Controller
public class SubmitQuizController {

	@PostMapping
	public String submitQuiz(@Valid @ModelAttribute(QuizConstants.QUIZ_ATTR) QuizFormRequest quiz,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

		if (bindingResult.hasErrors()) {
			redirectAttributes.addAttribute("id", quiz.getId());
			redirectAttributes.addFlashAttribute(
					"org.springframework.validation.BindingResult." + QuizConstants.QUIZ_ATTR, bindingResult);
			redirectAttributes.addFlashAttribute(QuizConstants.QUIZ_ATTR, quiz);
			return "redirect:" + QuizConstants.QUIZ_URL;
		}
		
		redirectAttributes.addFlashAttribute(QuizConstants.QUIZ_ATTR, quiz);

		return "redirect:" + ScoreConstants.SCORE_QUIZ_URL;
	}

}
