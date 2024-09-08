package fr.stephanj.app.quizzako.presentation.quiz.response;

public class BasicQuizResponse {

	private Long id;
	private String name;
	private String categoryName;

	public BasicQuizResponse(Long id, String name, String categoryName) {
		this.id = id;
		this.name = name;
		this.categoryName = categoryName;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCategoryName() {
		return categoryName;
	}
}
