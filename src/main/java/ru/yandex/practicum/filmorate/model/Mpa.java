package ru.yandex.practicum.filmorate.model;

import lombok.Data;

@Data
public class Mpa {
    int mpa_id;
    String mpa_name;

    public Mpa(int mpa_id, String mpa_name) {
        this.mpa_id = mpa_id;
        this.mpa_name = mpa_name;
    }

    public int getMpa_id() {
        return mpa_id;
    }

    public void setMpa_id(int mpa_id) {
        this.mpa_id = mpa_id;
    }

    public String getMpa_name() {
        return mpa_name;
    }

    public void setMpa_name(String mpa_name) {
        this.mpa_name = mpa_name;
    }
}
