package fr.stephanj.app.quizzako.presentation.cohort.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.stephanj.app.quizzako.application.cohort.ShowUsersToSelectMembersUseCase;
import fr.stephanj.app.quizzako.application.cohort.UpdateCohortUseCase;
import fr.stephanj.app.quizzako.application.cohort.ViewCohortUseCase;
import fr.stephanj.app.quizzako.presentation.cohort.common.CohortConstants;
import fr.stephanj.app.quizzako.presentation.cohort.request.FullCohortFormRequest;
import fr.stephanj.app.quizzako.presentation.user.response.UserFullNameWithMailResponse;
import jakarta.validation.Valid;

@Controller
public class ViewAndUpdateCohortController {

	@Autowired
	private ViewCohortUseCase viewUseCase;

	@Autowired
	private UpdateCohortUseCase updateUseCase;

	@Autowired
	private ShowUsersToSelectMembersUseCase showUsersUseCase;

	@GetMapping(CohortConstants.COHORT_DETAIL_URL)
	public String viewCohortDetails(@RequestParam(value = "id", required = true) Long id, Model model,
			@AuthenticationPrincipal UserDetails user) {

		if (!model.containsAttribute(CohortConstants.COHORT_ATTR)) {
			FullCohortFormRequest dto = viewUseCase.getCompleteViewOfMyCohort(id, user.getUsername());
			model.addAttribute(CohortConstants.COHORT_ATTR, dto);
		}

		List<UserFullNameWithMailResponse> users = showUsersUseCase.getUsersListToSelectMembers();
		model.addAttribute(CohortConstants.MEMBERS_LIST_ATTR, users);

		return CohortConstants.COHORT_DETAIL_PAGE;
	}

	@PostMapping(CohortConstants.COHORT_UPDATE_URL)
	public String updateCohort(@Valid @ModelAttribute(CohortConstants.COHORT_ATTR) FullCohortFormRequest form,
			@RequestParam(value = "id", required = true) Long id, Model model,
			@AuthenticationPrincipal UserDetails user, RedirectAttributes redirectAttributes,
			BindingResult bindingResult) {

		redirectAttributes.addAttribute("id", form.getId());

		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute(
					"org.springframework.validation.BindingResult." + CohortConstants.COHORT_ATTR, bindingResult);
			redirectAttributes.addFlashAttribute(CohortConstants.COHORT_ATTR, form);
			return "redirect:/" + CohortConstants.COHORT_DETAIL_URL;
		}

		updateUseCase.updateCohort(form, user.getUsername());

		redirectAttributes.addFlashAttribute(CohortConstants.SUCCESS_MESSAGE_ATTR, "Cohort updated successfully");

		return "redirect:/" + CohortConstants.COHORT_DETAIL_URL;
	}
}
