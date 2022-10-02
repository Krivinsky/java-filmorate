package ru.yandex.practicum.filmorate.model;

public class ErrorResponse {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ErrorResponse(String name) {
        this.name = name;
    }
}
