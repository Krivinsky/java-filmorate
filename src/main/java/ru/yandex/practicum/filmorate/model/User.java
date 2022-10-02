package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Data
public class User {

    int id;

    @Email
    private String email;

    @NotBlank
    private String login;

    private String name;

    private LocalDate birthday;

    @JsonIgnore
    Set<Integer> friends = new HashSet<>();

    @Override
    public String toString() {
        return "User{" +
                "userId=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}


