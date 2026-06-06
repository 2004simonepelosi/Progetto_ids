package it.unicam.Hackathon.validator;

public interface Validator<T> {
    void validate(T entity);
}