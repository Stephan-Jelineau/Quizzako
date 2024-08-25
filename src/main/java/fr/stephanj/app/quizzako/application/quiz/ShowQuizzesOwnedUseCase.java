package fr.stephanj.app.quizzako.application.quiz;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.stephanj.app.quizzako.domain.Quiz;
import fr.stephanj.app.quizzako.domain.repository.QuizRepository;
import fr.stephanj.app.quizzako.domain.repository.UserRepository;
import fr.stephanj.app.quizzako.presentation.quiz.response.QuizWithNumberOfQuestionResponse;

@Component
public class ShowQuizzesOwnedUseCase {

	@Autowired
	QuizRepository quizRepo;

	@Autowired
	UserRepository userRepo;

	@Transactional
	public List<QuizWithNumberOfQuestionResponse> getQuizzesByUserName(String userMail) {
		Long userId = userRepo.getUserIdByMail(userMail);
		List<Quiz> quizzes = quizRepo.getQuizzesByOwnerId(userId);

		if (quizzes == null)
			return null;

		List<Quiz> sortedQuizzes = quizzes.stream().sorted(Comparator.comparing(Quiz::getCreationDate).reversed())
				.collect(Collectors.toList());

		List<QuizWithNumberOfQuestionResponse> quizzesDto = new LinkedList<>();

		sortedQuizzes.forEach(q -> {
			QuizWithNumberOfQuestionResponse dto = new QuizWithNumberOfQuestionResponse(q.getId(), q.getName(),
					q.getCategory() != null ? q.getCategory().getName() : "None", q.getQuestions().size(),
					q.getCreationDate().toString());
			quizzesDto.add(dto);
		});

		return quizzesDto;
	}

}
