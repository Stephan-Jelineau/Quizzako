package fr.stephanj.app.quizzako;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.stephanj.app.quizzako.application.user.service.EncryptionService;
import fr.stephanj.app.quizzako.domain.Question;
import fr.stephanj.app.quizzako.domain.Quiz;
import fr.stephanj.app.quizzako.domain.Role;
import fr.stephanj.app.quizzako.domain.User;
import fr.stephanj.app.quizzako.domain.repository.QuizRepository;
import fr.stephanj.app.quizzako.domain.repository.UserRepository;

@Component
public class OnStartup implements ApplicationRunner {

	private static final String ADMIN_EMAIL = "admin@admin.fr";

	@Autowired
	UserRepository userRepository;

	@Autowired
	QuizRepository quizRepo;

	@Autowired
	EncryptionService encryptionService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		createAdminUser();
		createQuizzes();
	}

	private void createAdminUser() {
		if (userRepository.existsByEmail(ADMIN_EMAIL))
			return;
		User user = new User("admin", "admin", ADMIN_EMAIL, encryptionService.encode("admin"), Role.ADMIN);
		userRepository.registerNewUser(user);
	}

	@Transactional
	private void createQuizzes() {
		List<Quiz> quizzes = quizRepo.getNumberRequestedOfQuiz(12);

		if (quizzes.size() >= 12)
			return;

		List<Quiz> quizzesRegister = new ArrayList<>();
		for (int i = 0; i <= 15; i++) {
			List<Question> questions = new ArrayList<>();
			for (int j = 0; j <= 2; j++) {
				questions.add(new Question("Question n° " + j, new HashMap<String, String>() {
					private static final long serialVersionUID = 1L;
					{
						put("answer_1", "1");
						put("answer_2", "2");
						put(Question.GOOD_ANSWER, "3");
					}
				}));
			}
			Quiz quizRegister = new Quiz("Quiz n° " + i, questions, null);
			quizzesRegister.add(quizRegister);
		}

		quizzesRegister.forEach(quizRepo::saveQuiz);
	}

}
