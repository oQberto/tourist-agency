package by.travel.touristagency.service;

import by.travel.touristagency.dto.UserDto;
import by.travel.touristagency.entity.Profile;
import by.travel.touristagency.entity.User;
import by.travel.touristagency.exception.ValidationException;
import by.travel.touristagency.mapper.UserDtoMapper;
import by.travel.touristagency.mapper.UserMapper;
import by.travel.touristagency.repository.ProfileRepository;
import by.travel.touristagency.repository.UserRepository;
import by.travel.touristagency.validator.CreateUserValidator;
import by.travel.touristagency.validator.ValidationResult;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import java.util.Optional;

@RequiredArgsConstructor
public class UserService {
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final SessionFactory sessionFactory;
    private final UserDtoMapper userDtoMapper;
    private final UserMapper userMapper;
    private UserRepository userRepository;
    private ProfileRepository profileRepository;

    public void createUser(UserDto userDto) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            userRepository = new UserRepository(session);
            profileRepository = new ProfileRepository(session);

            User user = userDtoMapper.map(userDto);
            userRepository.save(user);

            Profile profile = Profile.builder()
                    .user(user)
                    .build();
            profileRepository.save(profile);

            session.getTransaction().commit();
        } catch (ConstraintViolationException e) {
            ValidationResult validationResult = createUserValidator.isValid(false);
            throw new ValidationException(validationResult.getErrors());
        }
    }

    public Optional<UserDto> login(String email, String password) {
        Optional<UserDto> userDto;

        try (Session session = sessionFactory.openSession()) {
            userRepository = new UserRepository(session);
            session.beginTransaction();

            userDto = userRepository.findByEmailAndPassword(email, password)
                    .map(userMapper::map);

            session.getTransaction().commit();
        }

        return userDto;
    }

    public void updateUser(UserDto userDto) {
        try (Session session = sessionFactory.openSession()) {
            userRepository = new UserRepository(session);
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
            userRepository = new UserRepository(session);
            session.beginTransaction();

            user = userRepository.findById(id);

            session.getTransaction().commit();
        }

        return user;
    }
}
