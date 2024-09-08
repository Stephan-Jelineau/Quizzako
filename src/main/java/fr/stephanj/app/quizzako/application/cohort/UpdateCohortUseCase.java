package fr.stephanj.app.quizzako.application.cohort;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.stephanj.app.quizzako.domain.Cohort;
import fr.stephanj.app.quizzako.domain.User;
import fr.stephanj.app.quizzako.domain.repository.CohortRepository;
import fr.stephanj.app.quizzako.domain.repository.UserRepository;
import fr.stephanj.app.quizzako.presentation.cohort.request.FullCohortFormRequest;

@Component
public class UpdateCohortUseCase {

	@Autowired
	UserRepository userRepo;

	@Autowired
	CohortRepository cohortRepo;

	public void updateCohort(FullCohortFormRequest form, String mailOwner) {
		Long idOwner = userRepo.getUserIdByMail(mailOwner);

		Cohort cohort = cohortRepo.getCohortByIdOwnedByUserId(form.getId(), idOwner);

		List<User> users = new ArrayList<>();

		for (String userMail : form.getMembers()) {
			User user = userRepo.getUserByEmail(userMail);
			users.add(user);
		}

		cohort.updateMembers(users);
		cohort.updateName(form.getName());

		cohortRepo.saveCohort(cohort);
	}

}
