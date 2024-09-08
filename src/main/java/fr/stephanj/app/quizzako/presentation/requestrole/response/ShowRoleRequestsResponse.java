package fr.stephanj.app.quizzako.presentation.requestrole.response;

public class ShowRoleRequestsResponse {
	private Long id;
	private boolean isActive;
	private String user;
	private String roleRequested;
	private String openDate;
	private String closeDate;

	public ShowRoleRequestsResponse(Long id, boolean isActive, String user, String roleRequested, String openDate, String closeDate) {
		this.id = id;
		this.isActive = isActive;
		this.user = user;
		this.roleRequested = roleRequested;
		this.openDate = openDate;
		this.closeDate = closeDate;
	}

	public Long getId() {
		return id;
	}

	public boolean isActive() {
		return isActive;
	}

	public String getUser() {
		return user;
	}

	public String getOpenDate() {
		return openDate;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public String getRoleRequested() {
		return roleRequested;
	}
}
