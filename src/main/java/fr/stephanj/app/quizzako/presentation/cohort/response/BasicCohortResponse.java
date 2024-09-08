package fr.stephanj.app.quizzako.presentation.cohort.response;

import java.time.LocalDate;

public class BasicCohortResponse {
	private Long id;
	private String name;
	private LocalDate creationDate;
	private int sizeMembers;

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

	public void setCreationDate(LocalDate localDate) {
		this.creationDate = localDate;
	}

	public int getSizeMembers() {
		return sizeMembers;
	}

	public void setSizeMembers(int sizeMembers) {
		this.sizeMembers = sizeMembers;
	}

}
