package com.jiuzhang.userMangement.dto;

import com.jiuzhang.userMangement.model.User;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class UserDTO {
    @NotNull(message = "username can not be null")
    @Size(min = 2, max = 20, message = "username length should between 2-20")
    private String username;


    @Email(message = "should enter valid email")
    private String email;

    private long id;
    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
    public UserDTO(){

    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }

}
