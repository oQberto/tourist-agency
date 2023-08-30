package by.travel.touristagency.repository;

import by.travel.touristagency.entity.Profile;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;

import java.util.Optional;

import static by.travel.touristagency.entity.QProfile.profile;

public class ProfileRepository extends BaseRepository<Long, Profile> {

    public ProfileRepository(EntityManager entityManager) {
        super(Profile.class, entityManager);
    }

    public Optional<Profile> getProfileByUserId(Long userId) {
        return Optional.ofNullable(
                new JPAQuery<Profile>(getEntityManager())
                        .select(profile)
                        .from(profile)
                        .where(profile.user.id.eq(userId))
                        .fetchOne()
        );
    }
}
