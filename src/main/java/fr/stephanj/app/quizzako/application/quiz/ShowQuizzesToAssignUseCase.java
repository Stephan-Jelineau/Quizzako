package fr.stephanj.app.quizzako.application.quiz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.stephanj.app.quizzako.domain.Cohort;
import fr.stephanj.app.quizzako.domain.Quiz;
import fr.stephanj.app.quizzako.domain.repository.CohortRepository;
import fr.stephanj.app.quizzako.domain.repository.QuizRepository;
import fr.stephanj.app.quizzako.domain.repository.UserRepository;
import fr.stephanj.app.quizzako.presentation.cohort.response.CohortQuizReponse;
import fr.stephanj.app.quizzako.presentation.quiz.response.QuizToAssignResponse;

@Component
public class ShowQuizzesToAssignUseCase {

	@Autowired
	UserRepository userRepo;

	@Autowired
	QuizRepository quizRepo;

	@Autowired
	CohortRepository cohortRepo;

	public CohortQuizReponse getQuizzesByUserNameForCohort(String userMail, Long cohortId) {
		Long userId = userRepo.getUserIdByMail(userMail);
		List<Quiz> quizzes = quizRepo.getQuizzesByOwnerId(userId);
		List<Long> quizzesId = quizRepo.getQuizzesIdAssignedByCohortId(cohortId);
		Cohort cohort = cohortRepo.getCohortByIdOwnedByUserId(cohortId, userId);
		List<QuizToAssignResponse> dtos = new ArrayList<>();

		for (Quiz quiz : quizzes) {
			QuizToAssignResponse dto = new QuizToAssignResponse();
			dto.setCreationDate(quiz.getCreationDate().toString());
			dto.setName(quiz.getName());
			dto.setId(quiz.getId());
			dtos.add(dto);
		}

		CohortQuizReponse dto = new CohortQuizReponse();
		dto.setQuizzes(dtos);
		dto.setCohortId(cohort.getId());
		dto.setCohortName(cohort.getName());
		dto.setIds(quizzesId);

		return dto;
	}

}
