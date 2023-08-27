package by.travel.touristagency.service;

import by.travel.touristagency.dto.CreateUserDto;
import by.travel.touristagency.dto.UserDto;
import by.travel.touristagency.entity.User;
import by.travel.touristagency.mapper.CreateUserMapper;
import by.travel.touristagency.mapper.UserMapper;
import by.travel.touristagency.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();
    private final UserRepository userRepository = new UserRepository(null);

    public void createUser(CreateUserDto createUserDto, SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            userRepository.setEntityManager(session);

            User user = createUserMapper.map(createUserDto);
            userRepository.save(user);

            session.getTransaction().commit();
        }
    }

    public Optional<UserDto> login(String email, String password, SessionFactory sessionFactory) {
        Optional<UserDto> userDto;

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            userRepository.setEntityManager(session);

            userDto = userRepository.findByEmailAndPassword(email, password)
                    .map(userMapper::map);

            session.getTransaction().commit();
        }

        return userDto;
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
