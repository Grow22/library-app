package com.group.libraryapp.config.user;


import com.group.libraryapp.repository.user.UserJdbcRepository;
import com.group.libraryapp.service.user.UserService1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class UserConfiguration {


    //@Bean
    public UserJdbcRepository userRepository(JdbcTemplate jdbcTemplate) {
        return new UserJdbcRepository(jdbcTemplate);
    }

    //@Bean
    public UserService1 userService(UserJdbcRepository userRepository) {
        return new UserService1(userRepository);
    }
}
