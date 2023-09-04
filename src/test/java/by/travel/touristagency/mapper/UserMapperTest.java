package by.travel.touristagency.mapper;

import by.travel.touristagency.dto.UserDto;
import by.travel.touristagency.entity.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTest {
    private final UserMapper userMapper = UserMapper.getInstance();

    @Test
    void map() {
        User user = User.builder()
                .username("UName")
                .email("uname@gmail.com")
                .password("123")
                .build();

        UserDto actualResult = userMapper.map(user);
        UserDto expectedResult = UserDto.builder()
                .username("UName")
                .email("uname@gmail.com")
                .password("123")
                .build();

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}