package fr.stephanj.app.quizzako.presentation.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.stephanj.app.quizzako.application.category.ShowAvailableCategoriesUseCase;
import fr.stephanj.app.quizzako.presentation.category.common.CategoryConstants;
import fr.stephanj.app.quizzako.presentation.category.response.BasicCategoryResponse;

@RequestMapping(CategoryConstants.CATEGORIES_URL)
@Controller
public class ShowAllCategoryController {

	@Autowired
	private ShowAvailableCategoriesUseCase useCase;

	@GetMapping
	public String showCategoriesAvailable(Model model) {

		List<BasicCategoryResponse> categoriesDto = useCase.getAvailableCategories(12);
		model.addAttribute("categories", categoriesDto);

		return CategoryConstants.CATEGORIES_PAGE;
	}
}
