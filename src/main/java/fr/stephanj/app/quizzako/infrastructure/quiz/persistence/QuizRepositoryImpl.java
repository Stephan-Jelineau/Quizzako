package fr.stephanj.app.quizzako.infrastructure.quiz.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import fr.stephanj.app.quizzako.domain.Quiz;
import fr.stephanj.app.quizzako.domain.Role;
import fr.stephanj.app.quizzako.domain.exception.quiz.QuizNotFoundException;
import fr.stephanj.app.quizzako.domain.exception.user.UserNotFoundException;
import fr.stephanj.app.quizzako.domain.repository.QuizRepository;
import fr.stephanj.app.quizzako.infrastructure.quiz.entity.QuizEntity;
import fr.stephanj.app.quizzako.infrastructure.quiz.mapper.QuizEntityMapper;
import fr.stephanj.app.quizzako.infrastructure.user.persistence.JpaUserRepository;

public class QuizRepositoryImpl implements QuizRepository {

	@Autowired
	JpaQuizRepository jpaQuizRepo;

	@Autowired
	JpaUserRepository jpaUserRepo;

	@Override
	public List<Quiz> getNumberRequestedOfQuiz(int numberOfQuiz) {
		Long adminId = jpaUserRepo.getIdByRole(Role.ADMIN.toString());
		List<QuizEntity> randomQuizzesEntity = jpaQuizRepo.findRandomQuizzes(adminId, numberOfQuiz);
		if (randomQuizzesEntity.isEmpty())
			throw new QuizNotFoundException("No quizzes found");
		List<Quiz> quizzes = randomQuizzesEntity.stream().map(QuizEntityMapper::toDomain).toList();
		return quizzes;
	}

	@Override
	public void saveQuiz(Quiz quiz) {
		QuizEntity entity = QuizEntityMapper.toEntity(quiz);
		entity.setOwner(jpaUserRepo.findByEmail(quiz.getOwner().getEmail())
				.orElseThrow(() -> new UserNotFoundException("User not found, quiz cannot be saved !")));
		jpaQuizRepo.save(entity);
	}

	@Override
	public void deleteQuizById(Long id) {
		if (!jpaQuizRepo.existsById(id))
			throw new QuizNotFoundException("The Quiz with id " + id + " was not found");
		jpaQuizRepo.deleteById(id);
	}

	@Override
	public Quiz getById(Long id) {
		Optional<QuizEntity> quizEntity = jpaQuizRepo.findById(id);
		if (quizEntity.isEmpty())
			throw new QuizNotFoundException("The Quiz with id " + id + " was not found");
		return QuizEntityMapper.toDomain(quizEntity.get());
	}

	@Override
	public boolean isOwnerAdmin(Long id) {
		Optional<QuizEntity> quizEntity = jpaQuizRepo.findById(id);
		if (quizEntity.isEmpty())
			throw new QuizNotFoundException("The Quiz with id " + id + " was not found");
		return quizEntity.get().getOwner().getRole().equals(Role.ADMIN.toString());
	}

	@Override
	public List<Quiz> getQuizzesByOwnerId(Long userId) {
		List<QuizEntity> quizzesEntity = jpaQuizRepo.findQuizzesByOwner(userId);
		if (quizzesEntity.isEmpty())
			return null;
		List<Quiz> quizzes = quizzesEntity.stream().map(QuizEntityMapper::toDomain).toList();
		return quizzes;
	}

	@Override
	public Quiz getQuizByIdOwnedByUserId(Long idQuiz, Long idUser) {
		QuizEntity quizEntity = jpaQuizRepo.findQuizByIdWithOwnerId(idQuiz, idUser).orElseThrow(
				() -> new QuizNotFoundException("The Quiz with id " + idQuiz + " was not found or not owned by you"));
		return QuizEntityMapper.toDomain(quizEntity);
	}

	@Override
	public List<Quiz> getAssignedQuizzesByCohortId(Long id) {
		List<QuizEntity> allQuizByCohortId = jpaQuizRepo.findAllQuizByCohortId(id);

		if (!allQuizByCohortId.isEmpty())
			return allQuizByCohortId.stream().map(QuizEntityMapper::toDomain).toList();

		return null;
	}

	@Override
	public List<Quiz> getAssignedQuizzesByUserId(Long userId) {
		List<QuizEntity> allQuizByUserId = jpaQuizRepo.findAllAssignedQuizByUserId(userId);

		if (!allQuizByUserId.isEmpty())
			return allQuizByUserId.stream().map(QuizEntityMapper::toDomain).toList();

		return null;
	}

	@Override
	public List<Long> getQuizzesIdAssignedByCohortId(Long cohortId) {
		List<Long> ids = jpaQuizRepo.findAllQuizIdAssignedByCohortId(cohortId);
		return ids;
	}

	@Override
	public boolean isOwner(Long userId, Long quizId) {
		Optional<QuizEntity> quizEntity = jpaQuizRepo.findById(quizId);
		if (quizEntity.isEmpty())
			throw new QuizNotFoundException("The Quiz with id " + quizId + " was not found");
		return quizEntity.get().getOwner().getId().equals(userId);
	}

	@Override
	public void deleteAllQuizAssignedToCohort(Long cohortId) {
		jpaQuizRepo.deleteAllAssignedByIdToCohortId(cohortId);
	}

	@Override
	public void assignQuizToCohort(Long quizId, Long cohortId) {
		jpaQuizRepo.insertQuizToCohortAssigned(quizId, cohortId);
	}

}
