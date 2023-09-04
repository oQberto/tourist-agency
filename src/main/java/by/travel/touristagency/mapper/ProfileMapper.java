package by.travel.touristagency.mapper;

import by.travel.touristagency.dto.ProfileDto;
import by.travel.touristagency.entity.Profile;
import lombok.Generated;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ProfileMapper implements Mapper<ProfileDto, Profile> {
    private static volatile ProfileMapper instance = new ProfileMapper();

    @Override
    public Profile map(ProfileDto object) {
        return Profile.builder()
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .birthday(object.getBirthday())
                .build();
    }

    @Generated
    public static ProfileMapper getInstance() {
        ProfileMapper result = instance;
        if (result != null) {
            return result;
        }

        synchronized (ProfileMapper.class) {
            if (instance == null) {
                instance = new ProfileMapper();
            }
            return instance;
        }
    }
}
