package by.travel.touristagency.repository;

import by.travel.touristagency.entity.Profile;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;

import java.util.Optional;

import static by.travel.touristagency.entity.QProfile.profile;
import static by.travel.touristagency.entity.QUser.user;

public class ProfileRepository extends BaseRepository<Long, Profile> {
    private static volatile ProfileRepository instance;

    public ProfileRepository(Class<Profile> clazz, Session session) {
        super(clazz, session);
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

    public static ProfileRepository getInstance(Session session) {
        ProfileRepository result = instance;
        if (result != null) {
            return result;
        }

        synchronized (ProfileRepository.class) {
            if (instance == null) {
                instance = new ProfileRepository(Profile.class, session);
            }
            return instance;
        }
    }
}
