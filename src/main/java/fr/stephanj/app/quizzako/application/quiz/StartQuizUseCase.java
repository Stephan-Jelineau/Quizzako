package fr.stephanj.app.quizzako.application.quiz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.stephanj.app.quizzako.domain.Question;
import fr.stephanj.app.quizzako.domain.Quiz;
import fr.stephanj.app.quizzako.domain.User;
import fr.stephanj.app.quizzako.domain.repository.CohortRepository;
import fr.stephanj.app.quizzako.domain.repository.QuizRepository;
import fr.stephanj.app.quizzako.domain.repository.UserRepository;
import fr.stephanj.app.quizzako.presentation.question.request.QuestionAnswersFormRequest;
import fr.stephanj.app.quizzako.presentation.quiz.request.QuizFormRequest;

@Component
public class StartQuizUseCase {

	@Autowired
	QuizRepository quizRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	CohortRepository cohortRepo;

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

	public boolean canStartQuiz(Long id, String mail) {

		User user = null;

		if (mail != null)
			user = userRepo.getUserByEmail(mail);

		boolean isQuizPublic = quizRepo.isOwnerAdmin(id);

		if (User.canStartPublicQuiz(user) && isQuizPublic)
			return true;

		if (!isQuizPublic && User.canStartAssignedQuiz(user)) {
			List<Quiz> quizzes = quizRepo.getAssignedQuizzesByUserId(user.getId());
			if (quizzes.stream().map(Quiz::getId).toList().contains(id))
				return true;
		}

		return false;
	}
}
