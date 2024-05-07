package fr.stephanj.app.quizzako.presentation.requestrole.request;

import jakarta.validation.constraints.NotNull;

public class GrantRoleRequest {
	@NotNull
	private Long idRoleRequest;

	public Long getIdRoleRequest() {
		return idRoleRequest;
	}

	public void setIdRoleRequest(Long idRoleRequest) {
		this.idRoleRequest = idRoleRequest;
	}

}
