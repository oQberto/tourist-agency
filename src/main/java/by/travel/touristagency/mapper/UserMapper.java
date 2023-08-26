package by.travel.touristagency.mapper;

import by.travel.touristagency.dto.UserDto;
import by.travel.touristagency.entity.User;

public class UserMapper implements Mapper<User, UserDto> {

    @Override
    public UserDto map(User object) {
        return UserDto.builder()
                .username(object.getUsername())
                .email(object.getEmail())
                .birthday(object.getProfile().getBirthday())
                .build();
    }
}
