package by.travel.touristagency.validator;

public interface Validator<T, R> {
    ValidationResult isValid(T object, R repository);
}
