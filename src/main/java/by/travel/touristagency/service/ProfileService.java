package by.travel.touristagency.service;

import by.travel.touristagency.dto.ProfileDto;
import by.travel.touristagency.entity.Profile;
import by.travel.touristagency.repository.ProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ProfileService {
    private static final ProfileService INSTANCE = new ProfileService();
    private final ProfileRepository profileRepository = new ProfileRepository(null);

    public void updateProfile(ProfileDto profileDto, Long userId, SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            profileRepository.setSession(session);

            Profile profile = profileRepository
                    .findById(userId)
                    .orElseThrow(
                            () -> new EntityNotFoundException("Profile not found")
                    );
            profile.setFirstName(profileDto.getFirstName());
            profile.setLastName(profileDto.getLastName());
            profile.setBirthday(profileDto.getBirthday());

            profileRepository.update(profile);

            session.getTransaction().commit();
        }
    }

    public Profile getByUserId(Long userId, SessionFactory sessionFactory) {
        Profile profile;

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            profileRepository.setSession(session);

            profile = profileRepository
                    .getProfileByUserId(userId)
                    .orElseThrow(
                            () -> new EntityNotFoundException("Profile not found")
                    );

            session.getTransaction().commit();
        }

        return profile;
    }

    public static ProfileService getInstance() {
        return INSTANCE;
    }
}
