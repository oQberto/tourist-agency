package by.travel.touristagency.service;

import by.travel.touristagency.dto.ProfileDto;
import by.travel.touristagency.entity.Profile;
import by.travel.touristagency.repository.ProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Optional;

@RequiredArgsConstructor
public class ProfileService {
    private final SessionFactory sessionFactory;
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

    public Optional<Profile> getByUserId(Long userId) {
        Optional<Profile> profile;

        try (Session session = sessionFactory.openSession()) {
            profileRepository = new ProfileRepository(session);
            session.beginTransaction();

            profile = profileRepository.getProfileByUserId(userId);

            session.getTransaction().commit();
        }

        return profile;
    }
}
