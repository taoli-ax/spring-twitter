package com.jiuzhang.userMangement.web.controller;

import com.jiuzhang.userMangement.dto.UserDTO;
import com.jiuzhang.userMangement.exception.BusinessException;
import com.jiuzhang.userMangement.model.User;
import com.jiuzhang.userMangement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUser();
    }

    @GetMapping("/{id}")
    public  ResponseEntity<UserDTO> getUserById(@PathVariable long id) {
        User user = userService.findUserByIdOrNull(id);
        return ResponseEntity.ok(new UserDTO(user));
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) throws BusinessException {
        User user = userService.createUser(userDTO);
        return ResponseEntity.ok(new UserDTO(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable long id, @RequestBody UserDTO userDTODetails) {
        User user = userService.updateUser(id, userDTODetails);
        return ResponseEntity.ok(new UserDTO(user));
    }

}
