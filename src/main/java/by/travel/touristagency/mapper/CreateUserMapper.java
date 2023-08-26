package by.travel.touristagency.mapper;

import by.travel.touristagency.dto.CreateUserDto;
import by.travel.touristagency.entity.User;

public class CreateUserMapper implements Mapper<User, CreateUserDto> {

    @Override
    public CreateUserDto map(User object) {
        return CreateUserDto.builder()
                .username(object.getUsername())
                .password(object.getPassword())
                .email(object.getEmail())
                .build();
    }
}
