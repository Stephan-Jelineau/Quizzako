package fr.stephanj.app.quizzako.infrastructure.question.entity;

import java.util.Map;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import fr.stephanj.app.quizzako.infrastructure.quiz.entity.QuizEntity;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "question")
public class QuestionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String question;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "answer_map", joinColumns = @JoinColumn(name = "question_id"))
	@MapKeyColumn(name = "answer_key")
	@Column(name = "answer_value")
	@Cascade(value = {CascadeType.ALL})
	private Map<String, String> answers;

	@ManyToOne
	@JoinColumn(name = "quiz_id", nullable = false)
	private QuizEntity quiz;

	public QuestionEntity() {

	}

	public QuestionEntity(Long id, String question, Map<String, String> answers, QuizEntity quiz) {
		this.id = id;
		this.question = question;
		this.answers = answers;
		this.quiz = quiz;
	}

	public QuestionEntity(Long id, String question, Map<String, String> answers) {
		this.id = id;
		this.question = question;
		this.answers = answers;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Map<String, String> getAnswers() {
		return answers;
	}

	public void setAnswers(Map<String, String> answers) {
		this.answers = answers;
	}

	public Long getId() {
		return id;
	}

	public QuizEntity getQuiz() {
		return quiz;
	}

	public void setQuiz(QuizEntity quiz) {
		this.quiz = quiz;
	}

}
