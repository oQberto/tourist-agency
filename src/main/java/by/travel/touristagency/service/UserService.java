package by.travel.touristagency.service;

import by.travel.touristagency.dto.UserDto;
import by.travel.touristagency.entity.User;
import by.travel.touristagency.mapper.UserDtoMapper;
import by.travel.touristagency.mapper.UserMapper;
import by.travel.touristagency.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final UserMapper userMapper = UserMapper.getInstance();
    private final UserDtoMapper userDtoMapper = UserDtoMapper.getInstance();
    private final UserRepository userRepository = new UserRepository(null);

    public void createUser(UserDto userDto, SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            userRepository.setSession(session);

            User user = userDtoMapper.map(userDto);
            userRepository.save(user);

            session.getTransaction().commit();
        }
    }

    public Optional<UserDto> login(String email, String password, SessionFactory sessionFactory) {
        Optional<UserDto> userDto;

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            userRepository.setSession(session);

            userDto = userRepository.findByEmailAndPassword(email, password)
                    .map(userMapper::map);

            session.getTransaction().commit();
        }

        return userDto;
    }

    public void updateUser(UserDto userDto, SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            userRepository.setSession(session);

            User user = userRepository
                    .findById(userDto.getId())
                    .orElseThrow(
                            () -> new EntityNotFoundException("Entity not found")
                    );
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            user.setUsername(userDto.getUsername());

            userRepository.update(user);

            session.getTransaction().commit();
        }
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
