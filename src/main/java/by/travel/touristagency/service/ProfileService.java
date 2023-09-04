package by.travel.touristagency.service;

import by.travel.touristagency.dto.ProfileDto;
import by.travel.touristagency.entity.Profile;
import by.travel.touristagency.repository.ProfileRepository;
import by.travel.touristagency.util.HibernateSessionFactoryUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ProfileService {
    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getInstance().buildSessionFactory();
    private static volatile ProfileService instance;
    private  ProfileRepository profileRepository;

    public void updateProfile(ProfileDto profileDto, Long userId) {
        try (Session session = sessionFactory.openSession()) {
            profileRepository = new ProfileRepository(session);
            session.beginTransaction();

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

    public Profile getByUserId(Long userId) {
        Profile profile;

        try (Session session = sessionFactory.openSession()) {
            profileRepository = new ProfileRepository(session);
            session.beginTransaction();

            profile = profileRepository
                    .getProfileByUserId(userId)
                    .orElseThrow(
                            () -> new EntityNotFoundException("Profile not found")
                    );

            session.getTransaction().commit();
        }

        return profile;
    }

    @Generated
    public static ProfileService getInstance() {
        ProfileService result = instance;
        if (result != null) {
            return result;
        }

        synchronized (ProfileService.class) {
            if (instance == null) {
                instance = new ProfileService();
            }
            return instance;
        }
    }
}
