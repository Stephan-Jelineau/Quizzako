package fr.stephanj.app.quizzako.infrastructure.cohort.entity;

import java.time.LocalDate;
import java.util.List;

import fr.stephanj.app.quizzako.infrastructure.user.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "cohort")
public class CohortEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String name;
	
	@NotNull
	@Column(name = "creation_date")
	private LocalDate creationDate;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private UserEntity owner;
	
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "cohort_user",
        joinColumns = @JoinColumn(name = "cohort_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
	private List<UserEntity> members;
 	
	public CohortEntity() {}

	public CohortEntity(Long id, @NotBlank String name, @NotNull LocalDate creationDate, UserEntity owner, List<UserEntity> members) {
		this.id = id;
		this.name = name;
		this.creationDate = creationDate;
		this.owner = owner;
		this.members = members;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public UserEntity getOwner() {
		return owner;
	}

	public void setOwner(UserEntity owner) {
		this.owner = owner;
	}

	public List<UserEntity> getMembers() {
		return members;
	}

	public void setMembers(List<UserEntity> members) {
		this.members = members;
	}
}
