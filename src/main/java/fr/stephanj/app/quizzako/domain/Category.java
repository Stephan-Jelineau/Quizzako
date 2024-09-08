package fr.stephanj.app.quizzako.domain;

import java.util.Objects;

public class Category {
	private Long id;
	private String name;

	public Category(String name) {
		validateData(name);
		this.name = name;
	}

	public Category(Long id, String name) {
		validateData(id, name);
		this.id = id;
		this.name = name;
	}

	private void validateData(Long id, String name) {
		Objects.requireNonNull(id, "Category with null id is not allowed");
		validateData(name);
	}

	private void validateData(String name) {
		Objects.requireNonNull(name, "Category with null name is not allowed");
	}

	public String getName() {
		return name;
	}

	public void updateName(String name) {
		validateData(name);
		this.name = name;
	}

	public Long getId() {
		return id;
	}

}
