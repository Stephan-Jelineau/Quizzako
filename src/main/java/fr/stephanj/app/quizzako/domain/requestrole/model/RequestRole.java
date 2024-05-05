package fr.stephanj.app.quizzako.domain.requestrole.model;

import java.time.LocalDate;

import fr.stephanj.app.quizzako.domain.user.model.Role;
import fr.stephanj.app.quizzako.domain.user.model.User;

public class RequestRole {

	Long id;
	User user;
	Role roleResquested;
	boolean isActive;
	LocalDate openDate;
	LocalDate closeDate;

	public RequestRole(User user, Role roleResquested) {
		this.user = user;
		this.roleResquested = roleResquested;
		isActive = true;
		openDate = LocalDate.now();
	}

	public RequestRole(Long id, User user, Role roleResquested, boolean isActive, LocalDate openDate,
			LocalDate closeDate) {
		this.id = id;
		this.user = user;
		this.roleResquested = roleResquested;
		this.isActive = isActive;
		this.openDate = openDate;
		this.closeDate = closeDate;
	}
	
	public void closeRequest() {
		isActive = false;
		closeDate = LocalDate.now();
	}

	public boolean isActive() {
		return isActive;
	}

	public User getUser() {
		return user;
	}

	public Role getRoleResquested() {
		return roleResquested;
	}

	public LocalDate getOpenDate() {
		return openDate;
	}

	public LocalDate getCloseDate() {
		return closeDate;
	}

	public Long getId() {
		return id;
	}

}
