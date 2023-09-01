package by.travel.touristagency.service;

import by.travel.touristagency.dto.UserDto;
import by.travel.touristagency.entity.User;
import by.travel.touristagency.mapper.UserDtoMapper;
import by.travel.touristagency.mapper.UserMapper;
import by.travel.touristagency.repository.UserRepository;
import by.travel.touristagency.util.HibernateSessionFactoryUtil;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class UserService {
    private static volatile UserService instance;
    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getInstance().buildSessionFactory();
    private final UserDtoMapper userDtoMapper = UserDtoMapper.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();
    private UserRepository userRepository;

    public void createUser(UserDto userDto) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            userRepository = UserRepository.getInstance(session);

            User user = userDtoMapper.map(userDto);
            userRepository.save(user);

            session.getTransaction().commit();
        }
    }

    public Optional<UserDto> login(String email, String password) {
        Optional<UserDto> userDto;

        try (Session session = sessionFactory.openSession()) {
            userRepository = UserRepository.getInstance(session);
            session.beginTransaction();

            userDto = userRepository.findByEmailAndPassword(email, password)
                    .map(userMapper::map);

            session.getTransaction().commit();
        }

        return userDto;
    }

    public void updateUser(UserDto userDto) {
        try (Session session = sessionFactory.openSession()) {
            userRepository = UserRepository.getInstance(session);
            session.beginTransaction();

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

    public Optional<User> getUserById(Long id) {
        Optional<User> user;

        try (Session session = sessionFactory.openSession()) {
            userRepository = UserRepository.getInstance(session);
            session.beginTransaction();

            user = userRepository.findById(id);

            session.getTransaction().commit();
        }

        return user;
    }

    public static UserService getInstance() {
        UserService result = instance;
        if (result != null) {
            return result;
        }
        synchronized (UserService.class) {
            if (instance == null) {
                instance = new UserService();
            }
            return instance;
        }
    }
}
