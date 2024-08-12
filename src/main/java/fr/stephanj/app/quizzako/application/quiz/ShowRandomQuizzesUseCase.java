package fr.stephanj.app.quizzako.application.quiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.stephanj.app.quizzako.domain.Question;
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

	/**
	 * To be deleted
	 */
	@Transactional
	private void addData() {
		// For test, to be deleted -----------------------
		List<Quiz> quizzesRegister = new ArrayList<>();
		for (int i = 0; i <= 15; i++) {
			List<Question> questions = new ArrayList<>();
			for (int j = 0; j <= 2; j++) {
				questions.add(new Question("Question n° " + j, new HashMap<String, String>() {
					private static final long serialVersionUID = 1L;
					{
						put("answer_1", "1");
						put("answer_2", "2");
						put(Question.GOOD_ANSWER, "3");
					}
				}));
			}
			Quiz quizRegister = new Quiz("Quiz n° " + i, questions, null);
			quizzesRegister.add(quizRegister);
		}

		quizzesRegister.forEach(quizRepo::saveQuiz);

		// ----------------------------------------------
	}

}
