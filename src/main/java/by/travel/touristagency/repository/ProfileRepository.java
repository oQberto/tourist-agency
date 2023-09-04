package by.travel.touristagency.repository;

import by.travel.touristagency.entity.Profile;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.Generated;
import org.hibernate.Session;

import java.util.Optional;

import static by.travel.touristagency.entity.QProfile.profile;
import static by.travel.touristagency.entity.QUser.user;

public class ProfileRepository extends BaseRepository<Long, Profile> {

    @Generated
    public ProfileRepository(Session session) {
        super(Profile.class, session);
    }

    public Optional<Profile> getProfileByUserId(Long userId) {
        return Optional.ofNullable(
                new JPAQuery<Profile>(getSession())
                        .select(profile)
                        .from(profile)
                        .join(profile.user, user)
                        .where(user.id.eq(userId))
                        .fetchOne()
        );
    }
}
