package fr.stephanj.app.quizzako.infrastructure.quiz.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.stephanj.app.quizzako.domain.Quiz;
import fr.stephanj.app.quizzako.domain.exception.quiz.QuizNotFoundException;
import fr.stephanj.app.quizzako.domain.repository.QuizRepository;
import fr.stephanj.app.quizzako.infrastructure.quiz.entity.QuizEntity;
import fr.stephanj.app.quizzako.infrastructure.quiz.mapper.QuizEntityMapper;

public class QuizRepositoryImpl implements QuizRepository {

	@Autowired
	JpaQuizRepository jpaQuizRepo;

	@Override
	public List<Quiz> getNumberRequestedOfQuiz(int numberOfQuiz) {
		List<QuizEntity> randomQuizzesEntity = jpaQuizRepo.findRandomQuizzes(numberOfQuiz);
		if (randomQuizzesEntity.isEmpty())
			throw new QuizNotFoundException("No quizzes found");
		List<Quiz> quizzes = randomQuizzesEntity.stream().map(QuizEntityMapper::toDomain).toList();
		return quizzes;
	}

	@Override
	public void saveQuiz(Quiz quiz) {
		QuizEntity entity = QuizEntityMapper.toEntity(quiz);
		jpaQuizRepo.save(entity);
	}

	@Override
	public void deleteQuizById(Long id) {
		if (!jpaQuizRepo.existsById(id))
			throw new QuizNotFoundException("The Quiz with id " + id + " was not found");
		jpaQuizRepo.deleteById(id);
	}

}
