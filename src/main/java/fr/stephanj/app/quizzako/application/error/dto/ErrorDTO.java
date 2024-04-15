package fr.stephanj.app.quizzako.application.error.dto;

public class ErrorDTO {

	private String message;

	public ErrorDTO(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
