package fr.stephanj.app.quizzako.application.cohort;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.stephanj.app.quizzako.domain.repository.CohortRepository;
import fr.stephanj.app.quizzako.domain.repository.QuizRepository;
import fr.stephanj.app.quizzako.domain.repository.UserRepository;

@Component
public class AssignQuizUseCase {

	@Autowired
	CohortRepository cohortRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	QuizRepository quizRepo;

	@Transactional
	public void assignQuizzesToCohort(List<Long> ids, Long cohortId) {

		List<Long> alreadyAssignedQuiz = quizRepo.getQuizzesIdAssignedByCohortId(cohortId);

		if ((ids == null || ids.isEmpty()) && !alreadyAssignedQuiz.isEmpty()) {
			alreadyAssignedQuiz.forEach(id -> quizRepo.deleteAllQuizAssignedToCohort(cohortId));
			return;
		}

		if ((ids != null) && alreadyAssignedQuiz.isEmpty()) {
			ids.forEach(quizId -> quizRepo.assignQuizToCohort(quizId, cohortId));
			return;
		}

		quizRepo.deleteAllQuizAssignedToCohort(cohortId);
		ids.forEach(quizId -> quizRepo.assignQuizToCohort(quizId, cohortId));
	}
}
