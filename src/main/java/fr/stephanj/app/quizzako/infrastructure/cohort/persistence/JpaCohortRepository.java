package fr.stephanj.app.quizzako.infrastructure.cohort.persistence;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.stephanj.app.quizzako.infrastructure.cohort.entity.CohortEntity;

public interface JpaCohortRepository extends JpaRepository<CohortEntity, Long> {

	List<CohortEntity> findByOwnerId(Long id);

	@Query(value = "SELECT * FROM cohort WHERE id = :idCohort AND owner_id = :idUser", nativeQuery = true)
	Optional<CohortEntity> findCohortByIdWithOwnerId(@Param("idCohort") Long idCohort, @Param("idUser") Long idUser);

	@Query(value = "SELECT * FROM cohort c JOIN cohort_user cu ON cu.cohort_id = c.id JOIN cohort_quiz cq ON cu.cohort_id = cq.cohort_id WHERE cq.quiz_id = :quizId AND cu.user_id = :userId", nativeQuery = true)
	Optional<CohortEntity> findCohortByQuizIdAndUserId(@Param("quizId") Long quizId, @Param("userId") Long userId);

	@Query(value = "SELECT cq.assigned_date FROM cohort_quiz cq WHERE cq.quiz_id = :quizId AND cq.cohort_id = :cohortId", nativeQuery = true)
	Optional<LocalDateTime> findAssignedDateByQuizIdAndCohortId(@Param("quizId") Long quizId,
			@Param("cohortId") Long cohortId);

}
