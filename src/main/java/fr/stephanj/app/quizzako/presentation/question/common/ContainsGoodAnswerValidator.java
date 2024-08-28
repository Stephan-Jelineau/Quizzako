package fr.stephanj.app.quizzako.presentation.question.common;

import java.util.Map;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.NotNull;

public class ContainsGoodAnswerValidator implements ConstraintValidator<ContainsGoodAnswer, Map<String, String>> {

	@Override
    public boolean isValid(@NotNull Map<String, String> answers, ConstraintValidatorContext context) {
        return answers.containsKey("goodAnswer");
    }

}
