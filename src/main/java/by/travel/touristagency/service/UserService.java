package by.travel.touristagency.service;

import by.travel.touristagency.dto.CreateUserDto;
import by.travel.touristagency.dto.UserDto;
import by.travel.touristagency.entity.User;
import by.travel.touristagency.mapper.CreateUserMapper;
import by.travel.touristagency.mapper.UserMapper;
import by.travel.touristagency.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CreateUserMapper createUserMapper;
    private final UserMapper userMapper;

    public void createUser(CreateUserDto createUserDto) {
        User user = createUserMapper.map(createUserDto);

        userRepository.save(user);
    }

    public Optional<UserDto> login(User user) {
        return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())
                .map(userMapper::map);
    }
}
