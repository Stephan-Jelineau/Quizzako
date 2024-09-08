package fr.stephanj.app.quizzako.application.cohort;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.stephanj.app.quizzako.domain.Cohort;
import fr.stephanj.app.quizzako.domain.Quiz;
import fr.stephanj.app.quizzako.domain.Score;
import fr.stephanj.app.quizzako.domain.User;
import fr.stephanj.app.quizzako.domain.repository.CohortRepository;
import fr.stephanj.app.quizzako.domain.repository.QuizRepository;
import fr.stephanj.app.quizzako.domain.repository.ScoreRepository;
import fr.stephanj.app.quizzako.domain.repository.UserRepository;
import fr.stephanj.app.quizzako.presentation.cohort.response.BasicCohortScoreResponse;

@Component
public class ShowCohortsGlobalScoreView {

	@Autowired
	CohortRepository cohortRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	ScoreRepository scoreRepo;

	@Autowired
	QuizRepository quizRepo;

	public List<BasicCohortScoreResponse> getGlobalScoreByOwnedCohort(String mailUser) {
		Long ownerId = userRepo.getUserIdByMail(mailUser);
		List<Cohort> cohorts = cohortRepo.getCohortsByUserId(ownerId);

		List<BasicCohortScoreResponse> dtos = new ArrayList<>();

		for (Cohort cohort : cohorts) {

			BasicCohortScoreResponse dto = new BasicCohortScoreResponse();
			dto.setId(cohort.getId());
			dto.setName(cohort.getName());

			List<Quiz> quizzes = quizRepo.getAssignedQuizzesByCohortId(cohort.getId());

			if (quizzes == null || quizzes.isEmpty()) {
				dtos.add(dto);
				continue;
			}

			for (Quiz quiz : quizzes) {

				List<Boolean> existingScores = new ArrayList<>();
				List<User> members = cohort.getMembers();

				if (members == null || members.isEmpty())
					break;

				for (User user : members) {
					Boolean isScoreExisting = scoreRepo.existByQuizIdAndUserID(quiz.getId(), user.getId());
					existingScores.add(isScoreExisting);
				}

				BigDecimal partipationPercentage = Score.computeParticipationPercentage(existingScores.size(),
						existingScores.stream().filter(e -> e.equals(true)).count());

				dto.getQuizzesNameToAccomplishment().put(quiz.getName(), String.valueOf(partipationPercentage));
			}

			dtos.add(dto);
		}

		return dtos;
	}

}
