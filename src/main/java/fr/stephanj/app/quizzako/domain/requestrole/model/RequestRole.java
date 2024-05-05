package fr.stephanj.app.quizzako.domain.requestrole.model;

import java.time.LocalDate;

import fr.stephanj.app.quizzako.domain.user.model.Role;

public class RequestRole {
	
	Long id;
	Long userId;
	Role roleResquested;
	boolean isActive;
	LocalDate openDate;
	LocalDate closeDate;

	public RequestRole(Long userId, Role roleResquested) {
		this.userId = userId;
		this.roleResquested = roleResquested;
		isActive = true;
		openDate = LocalDate.now();
	}

	public void closeRequest() {
		isActive = false;
		closeDate = LocalDate.now();
	}

	public boolean isActive() {
		return isActive;
	}

	public Long getUserId() {
		return userId;
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
