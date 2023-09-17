package by.travel.touristagency.validator;

import lombok.Generated;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateUserValidator implements Validator<Boolean> {
    private static CreateUserValidator instance;

    @Override
    public ValidationResult isValid(Boolean isUserExist) {
        ValidationResult validationResult = new ValidationResult();

        if (!isUserExist) {
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
