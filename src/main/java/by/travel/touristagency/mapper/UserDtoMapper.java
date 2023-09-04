package by.travel.touristagency.mapper;

import by.travel.touristagency.dto.UserDto;
import by.travel.touristagency.entity.User;
import lombok.Generated;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserDtoMapper implements Mapper<UserDto, User> {
    private static volatile UserDtoMapper instance = new UserDtoMapper();

    @Override
    public User map(UserDto object) {
        return User.builder()
                .id(object.getId())
                .username(object.getUsername())
                .email(object.getEmail())
                .password(object.getPassword())
                .build();
    }

    @Generated
    public static UserDtoMapper getInstance() {
        UserDtoMapper result  = instance;
        if (result != null) {
            return result;
        }

        synchronized (UserDtoMapper.class) {
            if (instance == null) {
                instance = new UserDtoMapper();
            }
            return instance;
        }
    }
}
