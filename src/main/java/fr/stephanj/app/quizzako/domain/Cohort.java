package fr.stephanj.app.quizzako.domain;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Cohort {

	private Long id;
	private String name;
	private LocalDate creationDate;
	private User owner;
	private List<User> members;

	public Cohort(Long id, String name, LocalDate creationDate, User owner, List<User> members) {
		validateData(id, name, creationDate, owner, members);
		this.id = id;
		this.name = name;
		this.creationDate = creationDate;
		this.owner = owner;
		this.members = members;
	}

	public Cohort(String name, User owner) {
		validateData(name, owner);
		this.name = name;
		this.creationDate = LocalDate.now();
		this.owner = owner;
	}

	private void validateData(Long id, String name, LocalDate creationDate, User owner, List<User> members) {
		Objects.requireNonNull(id, "Cohort with null id not allowed");
		Objects.requireNonNull(creationDate, "Cohort with null creationDate not allowed");
		if (members != null && !members.isEmpty())
			validateRoleMembers(members);
		validateData(name, owner);
	}

	public void validateRoleMembers(List<User> members) {
		Iterator<User> iterator = members.iterator();
		while (iterator.hasNext()) {
			User member = iterator.next();
			if (!member.getRole().equals(Role.STUDENT)) {
				iterator.remove();
			}
		}
	}

	private void validateData(String name, User owner) {
		Objects.requireNonNull(name, "Cohort with null name not allowed");
		Objects.requireNonNull(owner, "Cohort with null owner not allowed");
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public User getOwner() {
		return owner;
	}

	public List<User> getMembers() {
		if (members != null)
			return Collections.unmodifiableList(members);
		return null;
	}

	public void updateMembers(List<User> users) {
		if (members != null && !members.isEmpty())
			validateRoleMembers(members);
		this.members = users;
	}

	public void updateName(String name) {
		Objects.requireNonNull(name, "Cohort with null name not allowed");
		this.name = name;
	}

}
