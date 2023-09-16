package by.travel.touristagency.validator;

import by.travel.touristagency.dto.UserDto;
import by.travel.touristagency.repository.UserRepository;
import lombok.Generated;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateUserValidator implements Validator<UserDto, UserRepository> {
    private static CreateUserValidator instance;

    @Override
    public ValidationResult isValid(UserDto object, UserRepository userRepository) {
        boolean isUserExist = userRepository
                .findByEmailAndPassword(object.getEmail(), object.getPassword())
                .isPresent();
        ValidationResult validationResult = new ValidationResult();

        if (isUserExist) {
            validationResult.add(Error.of("invalid.user", "This user is already existing."));
        }

        return validationResult;
    }

    @Generated
    public static CreateUserValidator getInstance() {
        CreateUserValidator result = instance;

        if (result != null) {
            return result;
        }

        synchronized (CreateUserValidator.class) {
            if (instance == null) {
                instance = new CreateUserValidator();
            }
            return instance;
        }
    }
}
