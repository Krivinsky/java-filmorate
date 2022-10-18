package ru.yandex.practicum.filmorate.model;

public class ErrorResponse {
    String name;

    String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ErrorResponse(String name, String description) {
        this.name = name;
        this.name = description;
    }
}
