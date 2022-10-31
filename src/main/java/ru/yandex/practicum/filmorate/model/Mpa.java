package ru.yandex.practicum.filmorate.model;

import lombok.Data;

@Data
public class Mpa {
    int id;
    String name;

    public Mpa(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
