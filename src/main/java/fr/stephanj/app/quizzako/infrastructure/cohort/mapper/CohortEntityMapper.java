package fr.stephanj.app.quizzako.infrastructure.cohort.mapper;

import fr.stephanj.app.quizzako.domain.Cohort;
import fr.stephanj.app.quizzako.infrastructure.cohort.entity.CohortEntity;
import fr.stephanj.app.quizzako.infrastructure.user.mapper.UserEntityMapper;

public class CohortEntityMapper {

	public static Cohort toDomain(CohortEntity entity) {
		return new Cohort(entity.getId(), entity.getName(), entity.getCreationDate(),
				UserEntityMapper.toDomain(entity.getOwner()),
				entity.getMembers() != null ? entity.getMembers().stream().map(UserEntityMapper::toDomain).toList()
						: null);
	}

	public static CohortEntity toEntity(Cohort domain) {
		return new CohortEntity(domain.getId(), domain.getName(), domain.getCreationDate(),
				UserEntityMapper.toEntity(domain.getOwner()),
				domain.getMembers() != null ? domain.getMembers().stream().map(UserEntityMapper::toEntity).toList()
						: null);
	}

}
