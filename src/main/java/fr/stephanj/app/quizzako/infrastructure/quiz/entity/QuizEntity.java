package fr.stephanj.app.quizzako.infrastructure.quiz.entity;

import java.time.LocalDate;
import java.util.List;

import fr.stephanj.app.quizzako.infrastructure.category.entity.CategoryEntity;
import fr.stephanj.app.quizzako.infrastructure.question.entity.QuestionEntity;
import fr.stephanj.app.quizzako.infrastructure.user.entity.UserEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "quiz")
public class QuizEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;

	@ManyToOne
	@JoinColumn(name = "owner_id")
	private UserEntity owner;

	@NotNull
	@Column(name = "creation_date")
	private LocalDate creationDate;

	@NotNull
	@OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<QuestionEntity> questions;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
	private CategoryEntity category;

	public QuizEntity() {

	}

	public QuizEntity(Long id, String name, UserEntity owner, LocalDate creationDate, List<QuestionEntity> questions,
			CategoryEntity category) {
		this.id = id;
		this.name = name;
		this.owner = owner;
		this.creationDate = creationDate;
		this.questions = questions;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserEntity getOwner() {
		return owner;
	}

	public void setOwner(UserEntity owner) {
		this.owner = owner;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public List<QuestionEntity> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionEntity> questions) {
		this.questions = questions;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public Long getId() {
		return id;
	}

}
