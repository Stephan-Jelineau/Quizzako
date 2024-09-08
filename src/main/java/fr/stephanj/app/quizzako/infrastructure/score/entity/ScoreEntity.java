package fr.stephanj.app.quizzako.infrastructure.score.entity;

import java.time.LocalDate;

import fr.stephanj.app.quizzako.infrastructure.quiz.entity.QuizEntity;
import fr.stephanj.app.quizzako.infrastructure.user.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "score", uniqueConstraints = { @UniqueConstraint(columnNames = { "quiz_id", "user_id" }) })
public class ScoreEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "quiz_id", nullable = false)
	private QuizEntity quiz;

	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;

	@NotBlank
	private String score;

	@Column(name = "submission_date")
	private LocalDate submissionDate;

	public ScoreEntity() {
	}

	public ScoreEntity(Long id, UserEntity user, QuizEntity quiz, String score, LocalDate submissionDate) {
		this.id = id;
		this.quiz = quiz;
		this.user = user;
		this.score = score;
		this.submissionDate = submissionDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public QuizEntity getQuiz() {
		return quiz;
	}

	public void setQuiz(QuizEntity quiz) {
		this.quiz = quiz;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public LocalDate getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(LocalDate submissionDate) {
		this.submissionDate = submissionDate;
	}

}
