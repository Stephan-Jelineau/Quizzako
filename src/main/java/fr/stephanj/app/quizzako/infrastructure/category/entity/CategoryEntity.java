package fr.stephanj.app.quizzako.infrastructure.category.entity;

import java.util.List;

import fr.stephanj.app.quizzako.infrastructure.quiz.entity.QuizEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "category")
public class CategoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;

	@OneToMany(mappedBy = "category")
	private List<QuizEntity> quizzes;

	public CategoryEntity(Long id, @NotBlank String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public List<QuizEntity> getQuizzes() {
		return quizzes;
	}

	public void setQuizzes(List<QuizEntity> quizzes) {
		this.quizzes = quizzes;
	}
}
