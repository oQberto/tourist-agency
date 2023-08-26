package by.travel.touristagency.mapper;

import by.travel.touristagency.dto.UserDto;
import by.travel.touristagency.entity.User;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserMapper implements Mapper<User, UserDto> {
    private static final UserMapper INSTANCE = new UserMapper();
    @Override
    public UserDto map(User object) {
        return UserDto.builder()
                .username(object.getUsername())
                .email(object.getEmail())
                .build();
    }

    public static UserMapper getInstance() {
        return INSTANCE;
    }
}
