package fr.stephanj.app.quizzako.application.cohort;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.stephanj.app.quizzako.domain.User;
import fr.stephanj.app.quizzako.domain.repository.UserRepository;
import fr.stephanj.app.quizzako.presentation.user.response.UserFullNameWithMailResponse;

@Component
public class ShowUsersToSelectMembersUseCase {

	@Autowired
	UserRepository userRepo;

	public List<UserFullNameWithMailResponse> getUsersListToSelectMembers() {
		List<User> users = userRepo.getUsersByRole();

		List<UserFullNameWithMailResponse> dtos = new ArrayList<>();

		for (User user : users) {
			dtos.add(new UserFullNameWithMailResponse(user.getFirstname(), user.getName(), user.getEmail()));
		}

		return dtos;
	}

}
