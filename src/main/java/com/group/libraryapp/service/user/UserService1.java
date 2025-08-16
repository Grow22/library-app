package com.group.libraryapp.service.user;

import com.group.libraryapp.controller.calculator.user.UserCreateRequest;
import com.group.libraryapp.controller.calculator.user.UserResponse;
import com.group.libraryapp.controller.calculator.user.UserUpdateRequest;
import com.group.libraryapp.repository.user.UserJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;


//@Service
public class UserService1 {

    private final UserJdbcRepository userRepository;

    public UserService1(UserJdbcRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void updateUser(UserUpdateRequest request) {

        if(userRepository.isUserNotExist(request.getId())) {
            throw new IllegalArgumentException();
        }
        userRepository.updateUserName(request.getName(), request.getId());
    }

    public void deleteUser(String name) {
        if(userRepository.isUserNotExist(name)) {
            throw new IllegalArgumentException();
        }

        userRepository.deleteUserByName(name);
    }

    public void saveUser(UserCreateRequest request) {
        userRepository.saveUser(request.getName(), request.getAge());
    }

    public List<UserResponse> getUsers() {
        return userRepository.getUserResponse();
    }
}
