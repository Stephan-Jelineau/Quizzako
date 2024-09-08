package fr.stephanj.app.quizzako.presentation.cohort.response;

import java.util.HashMap;
import java.util.Map;

public class BasicCohortScoreResponse {

	private Long id;
	private String name;
	private Map<String, String> quizzesNameToAccomplishment = new HashMap<>();
	
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
	public Map<String, String> getQuizzesNameToAccomplishment() {
		return quizzesNameToAccomplishment;
	}
	public void setQuizzesNameToAccomplishment(Map<String, String> quizzesNameToAccomplishment) {
		this.quizzesNameToAccomplishment = quizzesNameToAccomplishment;
	}
}
