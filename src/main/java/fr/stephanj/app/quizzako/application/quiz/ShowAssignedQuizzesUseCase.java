package fr.stephanj.app.quizzako.application.quiz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.stephanj.app.quizzako.domain.Cohort;
import fr.stephanj.app.quizzako.domain.Quiz;
import fr.stephanj.app.quizzako.domain.repository.CohortRepository;
import fr.stephanj.app.quizzako.domain.repository.QuizRepository;
import fr.stephanj.app.quizzako.domain.repository.ScoreRepository;
import fr.stephanj.app.quizzako.domain.repository.UserRepository;
import fr.stephanj.app.quizzako.presentation.score.response.ScoreWithCohortAndDetailsResponse;

@Component
public class ShowAssignedQuizzesUseCase {

	@Autowired
	QuizRepository quizRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	ScoreRepository scoreRepo;

	@Autowired
	CohortRepository cohortRepo;

	public List<ScoreWithCohortAndDetailsResponse> getQuizzesAssigned(String username) {

		Long userId = userRepo.getUserIdByMail(username);
		List<Quiz> quizzes = quizRepo.getAssignedQuizzesByUserId(userId);

		if (quizzes == null)
			return null;

		List<ScoreWithCohortAndDetailsResponse> dtos = new ArrayList<>();

		for (Quiz quiz : quizzes) {

			ScoreWithCohortAndDetailsResponse dto = new ScoreWithCohortAndDetailsResponse();

			dto.setQuizId(quiz.getId());
			dto.setQuizName(quiz.getName());
			dto.setNumberOfQuestion(quiz.getQuestions().size());

			Boolean score = scoreRepo.existByQuizIdAndUserID(quiz.getId(), userId);
			if (score)
				dto.setScore(String.valueOf(scoreRepo.getScoreByQuizIdAndUserId(quiz.getId(), userId).getScore()));

			Cohort cohort = cohortRepo.getCohortByQuizIdAndUserId(quiz.getId(), userId);

			dto.setCohortName(cohort.getName());
			dto.setAssignedDate(cohortRepo.getAssignedDateByQuizIdAndCohortId(quiz.getId(), cohort.getId()));

			dtos.add(dto);
		}

		return dtos;
	}

}
