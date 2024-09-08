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
public class ViewCohortUseCase {

	@Autowired
	CohortRepository cohortRepo;

	@Autowired
	UserRepository userRepo;

	public FullCohortFormRequest getCompleteViewOfMyCohort(Long idCohort, String mailUser) {

		Long idUser = userRepo.getUserIdByMail(mailUser);
		Cohort cohort = cohortRepo.getCohortByIdOwnedByUserId(idCohort, idUser);

		FullCohortFormRequest dto = new FullCohortFormRequest();

		dto.setId(cohort.getId());
		dto.setName(cohort.getName());
		dto.setCreationDate(cohort.getCreationDate());

		List<User> members = cohort.getMembers();

		if (members != null && !members.isEmpty()) {
			List<String> userMails = new ArrayList<>();

			for (User member : members) {
				userMails.add(member.getEmail());
			}

			dto.setMembers(userMails);
		}

		return dto;
	}

}
