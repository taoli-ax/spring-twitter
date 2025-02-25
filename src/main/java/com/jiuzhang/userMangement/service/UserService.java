package com.jiuzhang.userMangement.service;

import com.jiuzhang.userMangement.dto.UserDTO;
import com.jiuzhang.userMangement.exception.BusinessException;
import com.jiuzhang.userMangement.model.User;
import com.jiuzhang.userMangement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public User findUserByIdOrNull(long id) {
        return userRepository.findById(id).orElse(null);
    }
    @Transactional
    public User createUser(UserDTO userDTO) throws BusinessException {
        if(userRepository.existsByUsername(userDTO.getUsername())){
                throw new BusinessException("用户已存在");
        }
        User user = new User(userDTO);
        return userRepository.save(user);
    }

    public User updateUser(long id, UserDTO userDetail){
        return userRepository.findById(id).map(user -> {
                    user.setUsername(userDetail.getUsername());
                    user.setEmail(userDetail.getEmail());
                    return userRepository.save(user);
                }
        ).orElseThrow(() -> new RuntimeException("User Not Found"));
    }

    // soft delete
    public void deleteUserById(long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
        user.setDeleted(true);
        userRepository.save(user);
    }

}
