package by.travel.touristagency.mapper;

import by.travel.touristagency.dto.UserDto;
import by.travel.touristagency.entity.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserDtoMapperTest {
    private final UserDtoMapper userDtoMapper = UserDtoMapper.getInstance();

    @Test
    void map() {
        UserDto userDto = UserDto.builder()
                .username("UName")
                .email("uname@gmail.com")
                .password("123")
                .build();

        User actualResult = userDtoMapper.map(userDto);
        User expectedResult = User.builder()
                .username("UName")
                .email("uname@gmail.com")
                .password("123")
                .build();

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}