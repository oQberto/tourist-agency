package by.travel.touristagency.mapper;

import by.travel.touristagency.dto.ProfileDto;
import by.travel.touristagency.entity.Profile;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ProfileMapperTest {
    private final ProfileMapper profileMapper = ProfileMapper.getInstance();

    @Test
    void map() {
        ProfileDto profileDto = ProfileDto.builder()
                .firstName("FName")
                .lastName("LName")
                .birthday(LocalDate.of(2000, 1, 15))
                .build();

        Profile actualResult = profileMapper.map(profileDto);
        Profile expectedResult = Profile.builder()
                .firstName("FName")
                .lastName("LName")
                .birthday(LocalDate.of(2000, 1, 15))
                .build();

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}