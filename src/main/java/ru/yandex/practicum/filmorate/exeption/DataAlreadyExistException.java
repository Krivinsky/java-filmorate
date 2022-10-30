package ru.yandex.practicum.filmorate.exeption;

public class DataAlreadyExistException extends Exception {
    public DataAlreadyExistException (String s) {
        super(s);
    }
}
