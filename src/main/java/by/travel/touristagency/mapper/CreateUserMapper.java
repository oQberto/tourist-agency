package by.travel.touristagency.mapper;

import by.travel.touristagency.dto.CreateUserDto;
import by.travel.touristagency.entity.User;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateUserMapper implements Mapper<CreateUserDto, User> {
    private static final CreateUserMapper INSTANCE = new CreateUserMapper();

    @Override
    public User map(CreateUserDto object) {
        return User.builder()
                .username(object.getUsername())
                .password(object.getPassword())
                .email(object.getEmail())
                .build();
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
}
