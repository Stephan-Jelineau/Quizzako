package fr.stephanj.app.quizzako.application.quiz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.stephanj.app.quizzako.domain.Question;
import fr.stephanj.app.quizzako.domain.Quiz;
import fr.stephanj.app.quizzako.domain.repository.QuizRepository;
import fr.stephanj.app.quizzako.presentation.question.request.QuestionAnswersFormRequest;
import fr.stephanj.app.quizzako.presentation.quiz.request.QuizFormRequest;

@Component
public class StartQuizUseCase {

	@Autowired
	QuizRepository quizRepo;

	public QuizFormRequest getQuizSelected(Long id) {
		Quiz quiz = quizRepo.getById(id);

		QuizFormRequest form = new QuizFormRequest();
		form.setId(quiz.getId());
		form.setName(quiz.getName());
		form.setCategoryName(quiz.getCategory() != null ? quiz.getCategory().getName() : "No category");

		List<QuestionAnswersFormRequest> questionList = new ArrayList<>();

		for (Question question : quiz.getQuestions()) {
			QuestionAnswersFormRequest questionForm = new QuestionAnswersFormRequest();
			questionForm.setAnswers(question.getAnswers().values().stream().toList());
			questionForm.setId(question.getId());
			questionForm.setQuestion(question.getQuestion());
			questionList.add(questionForm);
		}

		form.setQuestions(questionList);

		return form;
	}

	public boolean isQuizPublic(Long id) {
		return quizRepo.isOwnerAdmin(id);
	}
}
