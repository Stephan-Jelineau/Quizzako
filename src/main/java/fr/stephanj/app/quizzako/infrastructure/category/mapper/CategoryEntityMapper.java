package fr.stephanj.app.quizzako.infrastructure.category.mapper;

import fr.stephanj.app.quizzako.domain.Category;
import fr.stephanj.app.quizzako.infrastructure.category.entity.CategoryEntity;

public class CategoryEntityMapper {
	public static Category toDomain(CategoryEntity entity) {
		return new Category(entity.getId(), entity.getName());
	}

	public static CategoryEntity toEntity(Category category) {
		return new CategoryEntity(category.getId(), category.getName());
	}
}
