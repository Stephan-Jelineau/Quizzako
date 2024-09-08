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
import fr.stephanj.app.quizzako.domain.Cohort;
import fr.stephanj.app.quizzako.domain.Question;
import fr.stephanj.app.quizzako.domain.Quiz;
import fr.stephanj.app.quizzako.domain.Role;
import fr.stephanj.app.quizzako.domain.User;
import fr.stephanj.app.quizzako.domain.exception.quiz.QuizNotFoundException;
import fr.stephanj.app.quizzako.domain.repository.CohortRepository;
import fr.stephanj.app.quizzako.domain.repository.QuizRepository;
import fr.stephanj.app.quizzako.domain.repository.UserRepository;

@Component
@Transactional
public class OnStartup implements ApplicationRunner {

	private static final String ADMIN_EMAIL = "admin@admin.fr";
	private static final String TEACHER_EMAIL = "teacher@teacher.fr";
	private static final String USER_EMAIL = "user@user.fr";

	private User adminUser;
	private User teacherUser;

	@Autowired
	UserRepository userRepository;

	@Autowired
	QuizRepository quizRepo;

	@Autowired
	CohortRepository cohortRepo;

	@Autowired
	EncryptionService encryptionService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		createAdminUser();
		createTeacherUser();
		createStudentsUser();
		createBasicUser();
		createQuizzes();
		createCohorts();
	}

	private void createAdminUser() {
		User user;
		if (userRepository.existsByEmail(ADMIN_EMAIL)) {
			user = userRepository.getUserByEmail(ADMIN_EMAIL);
		} else {
			user = new User("admin", "admin", ADMIN_EMAIL, encryptionService.encode("aaa"), Role.ADMIN);
			userRepository.registerNewUser(user);
		}
		this.adminUser = user;
	}

	private void createTeacherUser() {
		User user;
		if (userRepository.existsByEmail(TEACHER_EMAIL)) {

			user = userRepository.getUserByEmail(TEACHER_EMAIL);
		} else {
			user = new User("teacher", "teacher", TEACHER_EMAIL, encryptionService.encode("aaa"), Role.TEACHER);
			userRepository.registerNewUser(user);
		}
		this.teacherUser = user;
	}

	private void createStudentsUser() {
		for (int i = 0; i < 4; i++) {
			String email = "student" + i + "@student.fr";
			if (!userRepository.existsByEmail(email)) {
				User user = new User("student" + i, "student" + i, email, encryptionService.encode("aaa"),
						Role.STUDENT);
				userRepository.registerNewUser(user);
			}
		}
	}

	private void createBasicUser() {
		if (userRepository.existsByEmail(USER_EMAIL))
			return;
		User user = new User("user", "user", USER_EMAIL, encryptionService.encode("aaa"), Role.USER);
		userRepository.registerNewUser(user);
	}

	private void createQuizzes() {
		try {
			quizRepo.getNumberRequestedOfQuiz(12);
		} catch (QuizNotFoundException e) {

			List<Quiz> quizzesRegister = new ArrayList<>();

			for (int i = 0; i <= 12; i++) {

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

				Quiz quizRegister = new Quiz("Quiz n° " + i, i <= 5 ? adminUser : teacherUser, questions, null);
				quizzesRegister.add(quizRegister);
			}

			quizzesRegister.forEach(quizRepo::saveQuiz);
		}
	}

	private void createCohorts() {

		List<Cohort> cohorts = cohortRepo.getCohortsByUserId(teacherUser.getId());

		if (cohorts != null)
			return;

		List<Cohort> cohortsRegister = new ArrayList<>();

		for (int i = 0; i <= 5; i++) {
			cohortsRegister.add(new Cohort("Cohort n° " + i, teacherUser));
		}

		cohortsRegister.forEach(cohortRepo::saveCohort);
	}
}
