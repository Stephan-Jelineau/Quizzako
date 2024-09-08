package fr.stephanj.app.quizzako.domain.repository;

import java.util.List;

import fr.stephanj.app.quizzako.domain.Cohort;

public interface CohortRepository {
	public List<Cohort> getCohortsByUserId(Long id);
	
	void saveCohort(Cohort cohort);

	public Cohort getCohortByIdOwnedByUserId(Long idCohort, Long idUser);
}
