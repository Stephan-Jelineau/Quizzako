package fr.stephanj.app.quizzako.infrastructure.cohort.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.stephanj.app.quizzako.domain.Cohort;
import fr.stephanj.app.quizzako.domain.exception.cohort.CohortNotFoundException;
import fr.stephanj.app.quizzako.domain.exception.user.UserNotFoundException;
import fr.stephanj.app.quizzako.domain.repository.CohortRepository;
import fr.stephanj.app.quizzako.infrastructure.cohort.entity.CohortEntity;
import fr.stephanj.app.quizzako.infrastructure.cohort.mapper.CohortEntityMapper;
import fr.stephanj.app.quizzako.infrastructure.user.persistence.JpaUserRepository;

public class CohortRepositoryImpl implements CohortRepository {

	@Autowired
	JpaCohortRepository jpaCohortRepo;

	@Autowired
	JpaUserRepository jpaUserRepo;

	public List<Cohort> getCohortsByUserId(Long id) {
		List<CohortEntity> cohortsEntity = jpaCohortRepo.findByOwnerId(id);
		if (cohortsEntity.isEmpty())
			return null;
		List<Cohort> cohorts = cohortsEntity.stream().map(CohortEntityMapper::toDomain).toList();
		return cohorts;
	}

	@Override
	public void saveCohort(Cohort cohort) {
		CohortEntity entity = CohortEntityMapper.toEntity(cohort);
		entity.setOwner(jpaUserRepo.findByEmail(cohort.getOwner().getEmail())
				.orElseThrow(() -> new UserNotFoundException("User not found, cohort cannot be saved !")));
		jpaCohortRepo.save(entity);
	}

	@Override
	public Cohort getCohortByIdOwnedByUserId(Long idCohort, Long idUser) {
		CohortEntity entity = jpaCohortRepo.findQuizByIdWithOwnerId(idCohort, idUser).orElseThrow(
				() -> new CohortNotFoundException("The Cohort with id " + idCohort + " was not found or not owned by you"));
		return CohortEntityMapper.toDomain(entity);
	}
}
