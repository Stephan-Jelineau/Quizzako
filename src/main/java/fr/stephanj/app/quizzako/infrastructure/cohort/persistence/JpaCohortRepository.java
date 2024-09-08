package fr.stephanj.app.quizzako.infrastructure.cohort.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.stephanj.app.quizzako.infrastructure.cohort.entity.CohortEntity;

public interface JpaCohortRepository extends JpaRepository<CohortEntity, Long> {

	List<CohortEntity> findByOwnerId(Long id);

	@Query(value = "SELECT * FROM cohort WHERE id = :idCohort AND owner_id = :idUser" , nativeQuery = true)
	Optional<CohortEntity> findQuizByIdWithOwnerId(@Param("idCohort") Long idCohort, @Param("idUser") Long idUser);

}
