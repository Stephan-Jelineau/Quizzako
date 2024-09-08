package fr.stephanj.app.quizzako.application.score;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.stephanj.app.quizzako.domain.Question;
import fr.stephanj.app.quizzako.domain.Score;
import fr.stephanj.app.quizzako.domain.repository.QuestionRepository;
import fr.stephanj.app.quizzako.domain.repository.QuizRepository;
import fr.stephanj.app.quizzako.domain.repository.ScoreRepository;
import fr.stephanj.app.quizzako.domain.repository.UserRepository;
import fr.stephanj.app.quizzako.presentation.question.request.QuestionAnswersFormRequest;
import fr.stephanj.app.quizzako.presentation.quiz.request.QuizFormRequest;
import fr.stephanj.app.quizzako.presentation.score.response.ScoreQuizResponse;

@Component
public class ShowScoreAndSaveUseCase {

	@Autowired
	QuestionRepository questionRepo;

	@Autowired
	ScoreRepository scoreRepo;

	@Autowired
	QuizRepository quizRepo;

	@Autowired
	UserRepository userRepo;

	@Transactional
	public ScoreQuizResponse getScoreAndSaveIfAuthenticated(QuizFormRequest quizRequest, UserDetails user) {

		List<QuestionAnswersFormRequest> questionsDTO = quizRequest.getQuestions();
		List<Question> questions = questionsDTO.stream().map(QuestionAnswersFormRequest::getId)
				.map(questionRepo::getById).toList();

		Map<Long, String> answers = new HashMap<>();

		for (QuestionAnswersFormRequest question : questionsDTO) {
			answers.put(question.getId(), question.getSelectedAnswer());
		}

		BigDecimal score = Score.computeScore(questions, answers);

		if (user != null) {
			Score scoreDomain = new Score(quizRepo.getById(quizRequest.getId()),
					userRepo.getUserByEmail(user.getUsername()), score);
			scoreRepo.saveScore(scoreDomain);
		}

		ScoreQuizResponse dtoResponse = new ScoreQuizResponse(quizRequest.getName(), String.valueOf(score));

		return dtoResponse;
	}
}
