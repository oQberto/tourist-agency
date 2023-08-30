package by.travel.touristagency.mapper;

import by.travel.touristagency.dto.UserDto;
import by.travel.touristagency.entity.User;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserDtoMapper implements Mapper<UserDto, User> {
    private static final UserDtoMapper INSTANCE = new UserDtoMapper();

    @Override
    public User map(UserDto object) {
        return User.builder()
                .id(object.getId())
                .username(object.getUsername())
                .email(object.getEmail())
                .password(object.getPassword())
                .build();
    }

    public static UserDtoMapper getInstance() {
        return INSTANCE;
    }
}
