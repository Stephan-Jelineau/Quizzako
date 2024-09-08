package fr.stephanj.app.quizzako.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import fr.stephanj.app.quizzako.domain.exception.score.ScoreNotComputableException;

public class Score {

	private Long id;
	private Quiz quiz;
	private User user;
	private BigDecimal score;
	private LocalDate submissionDate;

	public Score(Long id, Quiz quiz, User user, BigDecimal score, LocalDate submissionDate) {
		validateData(id, quiz, user, score);
		Objects.requireNonNull(submissionDate, "Score with null submissionDate not allowed");
		this.id = id;
		this.quiz = quiz;
		this.user = user;
		this.score = score;
		this.submissionDate = submissionDate;
	}

	public Score(Quiz quiz, User user, BigDecimal score) {
		validateData(quiz, user, score);
		this.quiz = quiz;
		this.user = user;
		this.score = score;
		this.submissionDate = LocalDate.now();
	}

	private void validateData(Long id, Quiz quiz, User user, BigDecimal score) {
		Objects.requireNonNull(id, "Score with null id not allowed");
		validateData(quiz, user, score);

	}

	private void validateData(Quiz quiz, User user, BigDecimal score) {
		Objects.requireNonNull(quiz, "Score with null quiz is not allowed");
		Objects.requireNonNull(user, "Score with null user is not allowed");
		Objects.requireNonNull(score, "Score with no value score is not allowed");
	}

	public void updateScore(BigDecimal score) {
		Objects.requireNonNull(score, "Score with no value score is not allowed");
		this.score = score;
	}

	public BigDecimal computeAndUpdateScore(Map<Long, String> answers) {
		BigDecimal scorePercentage = computeScoreLogic(this.quiz.getQuestions(), answers);
		updateScore(scorePercentage);
		return scorePercentage;
	}

	public static BigDecimal computeScore(List<Question> questions, Map<Long, String> answers) {
		return computeScoreLogic(questions, answers);
	}

	public static BigDecimal computeParticipationPercentage(long numberOfParticipation,
			long numberOfRequiredParticipation) {
		if (numberOfRequiredParticipation == 0)
			return BigDecimal.ZERO;

		BigDecimal participation = new BigDecimal(numberOfParticipation);
		BigDecimal requiredParticipation = new BigDecimal(numberOfRequiredParticipation);
		BigDecimal percentage = participation.divide(requiredParticipation, 4, RoundingMode.HALF_UP)
				.multiply(new BigDecimal(100));
		BigDecimal roundedPercentage = new BigDecimal(Math.ceil(percentage.doubleValue()));

		return roundedPercentage;
	}

	private static BigDecimal computeScoreLogic(List<Question> questions, Map<Long, String> answers) {
		Integer countGoodAnswer = 0;
		for (Entry<Long, String> entry : answers.entrySet()) {

			Long questionId = entry.getKey();
			String selectedAnswer = entry.getValue();

			Question question = questions.stream().filter(q -> q.getId().equals(questionId)).findFirst()
					.orElseThrow(() -> new ScoreNotComputableException(
							"The question with id " + questionId + " was not found, cannot compute quiz score"));

			if (question.getGoodAnswerValue().equals(selectedAnswer))
				countGoodAnswer++;
		}

		BigDecimal scorePercentage = BigDecimal.valueOf(countGoodAnswer)
				.divide(BigDecimal.valueOf(answers.size()), 2, RoundingMode.HALF_UP).scaleByPowerOfTen(2);
		return scorePercentage;
	}

	public Long getId() {
		return id;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public User getUser() {
		return user;
	}

	public BigDecimal getScore() {
		return score;
	}

	public LocalDate getSubmissionDate() {
		return submissionDate;
	}
}
