package fr.stephanj.app.quizzako.presentation.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.stephanj.app.quizzako.application.quiz.UpdateQuizOwnedUseCase;
import fr.stephanj.app.quizzako.application.quiz.ViewQuizOwnedUseCase;
import fr.stephanj.app.quizzako.presentation.quiz.common.QuizConstants;
import fr.stephanj.app.quizzako.presentation.quiz.request.FullQuizFormRequest;
import jakarta.validation.Valid;

@RequestMapping(QuizConstants.UPDATE_MY_QUIZ_URL)
@Controller
public class UpdateMyQuizController {

	@Autowired
	UpdateQuizOwnedUseCase updateUseCase;

	@Autowired
	ViewQuizOwnedUseCase viewUseCase;

	@GetMapping
	public String viewMyQuiz(@RequestParam(value = "id", required = true) Long id, Model model,
			@AuthenticationPrincipal UserDetails user) {

		if (!model.containsAttribute(QuizConstants.QUIZ_ATTR)) {
			FullQuizFormRequest dto = viewUseCase.getCompleteViewOfMyQuiz(id, user.getUsername());
			model.addAttribute(QuizConstants.QUIZ_ATTR, dto);
		}

		return QuizConstants.UPDATE_MY_QUIZ_PAGE;
	}

	@PostMapping
	public String updateQuiz(@Valid @ModelAttribute(QuizConstants.QUIZ_ATTR) FullQuizFormRequest form,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			@AuthenticationPrincipal UserDetails user) {

		redirectAttributes.addAttribute("id", form.getId());

		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute(
					"org.springframework.validation.BindingResult." + QuizConstants.QUIZ_ATTR, bindingResult);
			redirectAttributes.addFlashAttribute(QuizConstants.QUIZ_ATTR, form);
			return "redirect:" + QuizConstants.UPDATE_MY_QUIZ_URL;
		}

		updateUseCase.updateQuiz(form, user.getUsername());

		redirectAttributes.addFlashAttribute(QuizConstants.SUCCESS_MESSAGE_ATTR, "Quiz updated successfully");
		return "redirect:" + QuizConstants.UPDATE_MY_QUIZ_URL;
	}

}
