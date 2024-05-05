package fr.stephanj.app.quizzako.infrastructure.requestrole.entity;

import java.time.LocalDate;

import fr.stephanj.app.quizzako.domain.requestrole.model.RequestRole;
import fr.stephanj.app.quizzako.infrastructure.user.entity.UserEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "request_role")
public class RequestRoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	UserEntity user;

	@NotBlank
	@Column(name = "role_resquested")
	String roleResquested;

	@Column(name = "is_active")
	boolean isActive;

	@NotNull
	@Column(name = "open_date")
	LocalDate openDate;

	@Column(name = "close_date")
	LocalDate closeDate;

	public RequestRoleEntity() {

	}

	public RequestRoleEntity(@NotNull RequestRole request, @NotNull UserEntity user) {
		this.user = user;
		this.roleResquested = request.getRoleResquested().toString();
		this.isActive = request.isActive();
		this.openDate = request.getOpenDate();

		if (request.getCloseDate() != null)
			this.closeDate = request.getCloseDate();
	}

	public Long getId() {
		return id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getRoleResquested() {
		return roleResquested;
	}

	public void setRoleResquested(String roleResquested) {
		this.roleResquested = roleResquested;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public LocalDate getOpenDate() {
		return openDate;
	}

	public void setOpenDate(LocalDate openDate) {
		this.openDate = openDate;
	}

	public LocalDate getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(LocalDate closeDate) {
		this.closeDate = closeDate;
	}
}
