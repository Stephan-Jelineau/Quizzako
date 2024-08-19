package fr.stephanj.app.quizzako.application.quiz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.stephanj.app.quizzako.domain.Quiz;
import fr.stephanj.app.quizzako.domain.repository.QuizRepository;
import fr.stephanj.app.quizzako.presentation.quiz.response.BasicQuizResponse;

@Component
public class ShowRandomQuizzesUseCase {

	@Autowired
	QuizRepository quizRepo;

	public List<BasicQuizResponse> getRandomQuizzes(int numberOfQuizRequested) {

		List<Quiz> quizzes = quizRepo.getNumberRequestedOfQuiz(numberOfQuizRequested);
		List<BasicQuizResponse> response = new ArrayList<>();

		quizzes.forEach(q -> {
			BasicQuizResponse quiz = new BasicQuizResponse(q.getId(), q.getName(),
					q.getCategory() != null ? q.getCategory().getName() : "No category");
			response.add(quiz);
		});

		return response;
	}
}
