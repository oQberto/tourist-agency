package by.travel.touristagency.service;

import by.travel.touristagency.dto.ProfileDto;
import by.travel.touristagency.entity.Profile;
import by.travel.touristagency.entity.User;
import by.travel.touristagency.util.HibernateTestUtil;
import by.travel.touristagency.util.TestDataImporter;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ProfileServiceTest {
    private static final SessionFactory SESSION_FACTORY = HibernateTestUtil.buildSessionFactory();
    private final ProfileService profileService = new ProfileService(SESSION_FACTORY);

    @BeforeAll
    public static void setUp() {
        TestDataImporter.importData(SESSION_FACTORY);
    }

    @Test
    void updateProfile() {
        ProfileDto profileDto = ProfileDto.builder()
                .firstName("First Name")
                .lastName("Last Name")
                .birthday(LocalDate.of(2000, 5, 20))
                .build();
        Profile profile = Profile.builder()
                .id(1L)
                .user(User.builder()
                        .id(1L)
                        .username("Uname")
                        .email("uname@gmail.com")
                        .password("123")
                        .build()
                )
                .firstName("FName")
                .lastName("LName")
                .birthday(LocalDate.of(2000, 9, 15))
                .build();

        profileService.updateProfile(profileDto, profile.getUser().getId());
        Optional<Profile> actualResult = profileService.getByUserId(profile.getUser().getId());
        assertThat(actualResult).isPresent();

        profile.setFirstName(profileDto.getFirstName());
        profile.setLastName(profileDto.getLastName());
        profile.setBirthday(profileDto.getBirthday());
        assertThat(actualResult.get()).isEqualTo(profile);
    }

    @Test
    void getByUserId() {
        Long userId = 2L;

        Optional<Profile> actualResult = profileService.getByUserId(userId);
        assertThat(actualResult).isPresent();

        Long profileId = actualResult.get().getId();
        assertThat(profileId).isEqualTo(userId);
    }
}