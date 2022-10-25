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

    int user_id;

    @Email
    private String email;

    @NotBlank
    private String login;

    private String name;

    private LocalDate birthday;

    public User(int user_id, String email, String login, String name, LocalDate birthday) {
        this.user_id = user_id;
        this.email = email;
        this.login = login;
        this.name = name;
        this.birthday = birthday;
    }



    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @JsonIgnore
    Set<Integer> friends = new HashSet<>();

    @Override
    public String toString() {
        return "User{" +
                "userId=" + user_id +
                ", name='" + name + '\'' +
                '}';
    }
}


