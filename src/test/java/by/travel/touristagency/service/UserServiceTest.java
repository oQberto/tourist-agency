package by.travel.touristagency.service;

import by.travel.touristagency.dto.UserDto;
import by.travel.touristagency.entity.User;
import by.travel.touristagency.mapper.UserDtoMapper;
import by.travel.touristagency.mapper.UserMapper;
import by.travel.touristagency.util.HibernateTestUtil;
import by.travel.touristagency.util.TestDataImporter;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;

class UserServiceTest {
    private static final SessionFactory SESSION_FACTORY = HibernateTestUtil.buildSessionFactory();
    private static final UserMapper USER_MAPPER = UserMapper.getInstance();
    private static final UserDtoMapper USER_DTO_MAPPER = UserDtoMapper.getInstance();
    private final UserService userService = new UserService(
            SESSION_FACTORY,
            USER_DTO_MAPPER,
            USER_MAPPER
    );

    @BeforeAll
    public static void setUp() {
        TestDataImporter.importData(SESSION_FACTORY);
    }

    @Test
    void createUser() {
        UserDto userDto = createUserDto();

        userService.createUser(userDto);
        Optional<User> savedUser = userService.getUserById(14L);

        assertThat(savedUser).isPresent();
    }

    @Test
    void loginSuccess() {
        String userEmail = getUser().getEmail();
        String userPassword = getUser().getPassword();

        Optional<UserDto> actualResult = userService.login(userEmail, userPassword);

        assertThat(actualResult).isPresent();
    }

    @Test
    void loginFail() {
        String userEmail = anyString();
        String userPassword = anyString();

        Optional<UserDto> actualResult = userService.login(userEmail, userPassword);

        assertThat(actualResult).isEmpty();
    }

    @Test
    void updateUser() {
        Optional<User> user = userService.getUserById(1L);
        assertThat(user).isPresent();
        User updatedUser = user.get();
        updatedUser.setUsername("New Name");
        updatedUser.setPassword("newPassword");

        UserDto userDto = USER_MAPPER.map(updatedUser);
        userService.updateUser(userDto);
        Optional<User> actualResult = userService.getUserById(1L);

        assertThat(actualResult).isPresent();
        assertThat(actualResult.get()).isEqualTo(updatedUser);
    }

    @Test
    void getUserById() {
        User user = getUser();

        Optional<User> actualResult = userService.getUserById(user.getId());
        assertThat(actualResult).isPresent();
        assertThat(actualResult.get()).isEqualTo(user);
    }

    private User getUser() {
        return User.builder()
                .id(2L)
                .username("UName1")
                .password("1231")
                .email("uname1@gmail.com")
                .build();
    }

    private UserDto createUserDto() {
        return UserDto.builder()
                .username("NewTestUser")
                .password("123")
                .email("newUser@gmail.com")
                .build();
    }
}