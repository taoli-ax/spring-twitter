package com.jiuzhang.userMangement.model;
import com.jiuzhang.userMangement.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String email;
    private boolean isDeleted=false;
    public User() {}
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User(UserDTO userDTO) {
        this.username = userDTO.getUsername();
        this.email = userDTO.getEmail();
    }
}
