package fr.stephanj.app.quizzako.presentation.requestrole.request;

import jakarta.validation.constraints.NotNull;

public class GrantRoleRequest {
	@NotNull
	private Long idRoleRequest;
	
	@NotNull
	private String type;

	public Long getIdRoleRequest() {
		return idRoleRequest;
	}

	public void setIdRoleRequest(Long idRoleRequest) {
		this.idRoleRequest = idRoleRequest;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
