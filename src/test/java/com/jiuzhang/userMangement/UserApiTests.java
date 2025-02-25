package com.jiuzhang.userMangement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiuzhang.userMangement.dto.UserDTO;
import com.jiuzhang.userMangement.model.User;
import com.jiuzhang.userMangement.repository.UserRepository;
import com.jiuzhang.userMangement.service.UserService;
import com.jiuzhang.userMangement.web.controller.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class UserApiTests {
    @InjectMocks
    UserController userController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    private UserDTO userDTO;

    @BeforeEach
    public void setup() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("linghu");
        userDTO.setEmail("linghu@gmail.com");
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testCreateUser() throws Exception {


        // 设置mockServer的行为
        User mockUser = new User();
        mockUser.setUsername(userDTO.getUsername());
        mockUser.setEmail(userDTO.getEmail());
        when(userService.createUser(userDTO)).thenReturn(mockUser);

        // userDTO to Json
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(userDTO);

        //
        mockMvc.perform(post("/users").
                contentType(MediaType.APPLICATION_JSON).
                content(userJson)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.username").value("linghu")).
                andExpect(jsonPath("$.email").value("linghu@gmail.com"));


        //
        verify(userService, times(1)).createUser(userDTO);


    }
    @Test
    public void testUpdateUser() throws Exception {
        User mockUser = new User();
        mockUser.setUsername("linghu-update");
        mockUser.setEmail(userDTO.getEmail());
        long id = mockUser.getId();
        when(userService.updateUser(id, userDTO)).thenReturn(mockUser);


        // 将 UserDTO 转换为 JSON 格式
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(userDTO);

        // put请求
        mockMvc.perform(put("/users/"+id).
                contentType(MediaType.APPLICATION_JSON).
                content(userJson)).
                andExpect(status().isOk()).
                andExpect(jsonPath("$.username").value("linghu-update")).
                andExpect(jsonPath("$.email").value("linghu@gmail.com"));

        verify(userService, times(1)).updateUser(id, userDTO);
    }
}
