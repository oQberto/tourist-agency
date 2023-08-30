package by.travel.touristagency.mapper;

import by.travel.touristagency.dto.ProfileDto;
import by.travel.touristagency.entity.Profile;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ProfileMapper implements Mapper<ProfileDto, Profile> {
    private static final ProfileMapper INSTANCE = new ProfileMapper();

    @Override
    public Profile map(ProfileDto object) {
        return Profile.builder()
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .birthday(object.getBirthday())
                .build();
    }

    public static ProfileMapper getInstance() {
        return INSTANCE;
    }
}
