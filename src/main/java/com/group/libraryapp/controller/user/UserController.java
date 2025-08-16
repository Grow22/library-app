package com.group.libraryapp.controller.user;


import com.group.libraryapp.controller.calculator.user.UserCreateRequest;
import com.group.libraryapp.controller.calculator.user.UserResponse;
import com.group.libraryapp.controller.calculator.user.UserUpdateRequest;
import com.group.libraryapp.service.user.UserService1;
import com.group.libraryapp.service.user.UserService2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService2 userService;

    public UserController(UserService2 userService) {
        this.userService =  userService;
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        userService.updateUser(request);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        userService.deleteUser(name);
    }

    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request) {
        userService.saveUser(request);
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }
}
