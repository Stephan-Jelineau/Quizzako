package fr.stephanj.app.quizzako.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import fr.stephanj.app.quizzako.domain.exception.requestrole.RoleRequestedAlreadyGranted;
import fr.stephanj.app.quizzako.domain.exception.requestrole.RoleRequestedNotAllowedException;

public final class RequestRole {

	Long id;
	User user;
	Role roleResquested;
	boolean isActive;
	String openDate;
	String closeDate;

	public RequestRole(User user, Role roleResquested) {
		validateData(user, roleResquested);
		validateNewRoleRequested(roleResquested);
		this.user = user;
		this.roleResquested = roleResquested;
		isActive = true;
		openDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

	public RequestRole(Long id, User user, Role roleResquested, boolean isActive, String openDate, String closeDate) {
		validateData(id, user, roleResquested, openDate);
		this.id = id;
		this.user = user;
		this.roleResquested = roleResquested;
		this.isActive = isActive;
		this.openDate = openDate;
		this.closeDate = closeDate;
	}

	private void validateData(Long id, User user, Role roleResquested, String openDate) {
		Objects.requireNonNull(id, "RequestRole with null id not allowed");
		validateData(user, roleResquested);
		Objects.requireNonNull(openDate, "RequestRole with null openDate not allowed");
	}

	private void validateData(User user, Role roleResquested) {
		Objects.requireNonNull(user, "RequestRole with null user not allowed");
		Objects.requireNonNull(roleResquested, "RequestRole with null roleResquested not allowed");

	}

	private void validateNewRoleRequested(Role roleResquested2) {
		if (!Role.isRequestableRole(roleResquested))
			throw new RoleRequestedNotAllowedException("The requested role is not allowed to be assigned");
		if (user.getRole().equals(roleResquested))
			throw new RoleRequestedAlreadyGranted(
					"The role [" + roleResquested + "] requested is already assigned to your account");
	}

	public void closeRequest() {
		isActive = false;
		closeDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
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

	public String getOpenDate() {
		return openDate;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public Long getId() {
		return id;
	}

}
