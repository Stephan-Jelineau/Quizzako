package fr.stephanj.app.quizzako.application.quiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.stephanj.app.quizzako.domain.Question;
import fr.stephanj.app.quizzako.domain.Quiz;
import fr.stephanj.app.quizzako.domain.repository.QuizRepository;
import fr.stephanj.app.quizzako.domain.repository.UserRepository;
import fr.stephanj.app.quizzako.presentation.question.request.QuestionWithGoodAnswerFormRequest;
import fr.stephanj.app.quizzako.presentation.quiz.request.FullQuizFormRequest;

@Component
public class ViewQuizOwnedUseCase {
	@Autowired
	QuizRepository quizRepo;

	@Autowired
	UserRepository userRepo;

	public FullQuizFormRequest getCompleteViewOfMyQuiz(Long idQuiz, String mailUser) {

		Long idUser = userRepo.getUserIdByMail(mailUser);
		Quiz quiz = quizRepo.getQuizByIdOwnedByUserId(idQuiz, idUser);

		List<QuestionWithGoodAnswerFormRequest> questionsDto = new ArrayList<>();

		for (Question question : quiz.getQuestions()) {
			QuestionWithGoodAnswerFormRequest questionDto = new QuestionWithGoodAnswerFormRequest();
			questionDto.setId(question.getId());
			questionDto.setQuestion(question.getQuestion());
			questionDto.setAnswers(new HashMap<>(question.getAnswers()));
			questionsDto.add(questionDto);
		}

		FullQuizFormRequest dto = new FullQuizFormRequest();
		dto.setId(idQuiz);
		dto.setCategoryName(quiz.getCategory() != null ? quiz.getCategory().getName() : "None");
		dto.setName(quiz.getName());
		dto.setQuestions(questionsDto);

		return dto;
	}
}
