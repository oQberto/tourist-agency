package by.travel.touristagency.mapper;

import by.travel.touristagency.dto.UserDto;
import by.travel.touristagency.entity.User;
import lombok.Generated;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserMapper implements Mapper<User, UserDto> {
    private static volatile UserMapper instance = new UserMapper();

    @Override
    public UserDto map(User object) {
        return UserDto.builder()
                .id(object.getId())
                .username(object.getUsername())
                .email(object.getEmail())
                .password(object.getPassword())
                .build();
    }

    @Generated
    public static UserMapper getInstance() {
        UserMapper result = instance;
        if (result != null) {
            return result;
        }

        synchronized (UserMapper.class) {
            if (instance == null) {
                instance = new UserMapper();
            }
            return instance;
        }
    }
}
