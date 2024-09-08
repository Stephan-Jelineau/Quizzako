package fr.stephanj.app.quizzako.application.cohort;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.stephanj.app.quizzako.domain.Cohort;
import fr.stephanj.app.quizzako.domain.User;
import fr.stephanj.app.quizzako.domain.repository.CohortRepository;
import fr.stephanj.app.quizzako.domain.repository.UserRepository;
import fr.stephanj.app.quizzako.presentation.cohort.response.BasicCohortResponse;

@Component
public class ShowMyCohortsUseCase {

	@Autowired
	CohortRepository cohortRepo;

	@Autowired
	UserRepository userRepo;

	public List<BasicCohortResponse> getMyCohorts(String username) {

		Long id = userRepo.getUserIdByMail(username);
		List<Cohort> cohorts = cohortRepo.getCohortsByUserId(id);

		List<BasicCohortResponse> dtos = new ArrayList<>();

		for (Cohort cohort : cohorts) {
			BasicCohortResponse dto = new BasicCohortResponse();
			dto.setId(cohort.getId());
			dto.setName(cohort.getName());
			dto.setCreationDate(cohort.getCreationDate());
			List<User> members = cohort.getMembers();
			if (members != null && !members.isEmpty())
				dto.setSizeMembers(members.size());
			dtos.add(dto);
		}

		return dtos;
	}

}
