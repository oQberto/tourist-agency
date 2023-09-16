package by.travel.touristagency.validator;

public interface Validator<T> {
    ValidationResult isValid(T object);
}
