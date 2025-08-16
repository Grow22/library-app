package com.group.libraryapp.controller.calculator.user;


import com.group.libraryapp.controller.Fruit;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//@RestController
public class UserController {

    private final List<User> users = new ArrayList<>();

    private final JdbcTemplate jdbcTemplate;


    public UserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request) {
        String sql = "INSERT INTO user(name, age) VALUES(?, ?)";
        jdbcTemplate.update(sql, request.getName(), request.getAge());
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() {

       String sql = "SELECT * FROM user";
       return jdbcTemplate.query(sql, new RowMapper<UserResponse>() {
           @Override
           public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
               long id = rs.getLong("id");
               String name = rs.getString("name");
               int age = rs.getInt("age");
               return new UserResponse(id, name, age);
           }
       });
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {

        String readSql = "select *  from user where id = ?";
        // 데이터가 있는 경우 비어 있지 않은 리스트 반환 -> isEmpty() -> 1 반환
        // 데이터가 없는 경우 비어 있는 리스트 반환 -> isEmpty() -> 0 반환
        boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, request.getId()).isEmpty();

        if(isUserNotExist) {
            throw new IllegalArgumentException();
        }
        String updateSql = "UPDATE user SET name = ? WHERE id = ?";
        jdbcTemplate.update(updateSql, request.getName(), request.getId());

    }

    // name 이 존재하지 않을 경우 예외 발생
    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {

        String readSql = "SELECT * FROM user WHERE name = ?";
        boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();

        if(isUserNotExist) {
            throw new IllegalArgumentException();
        }

        String deleteSql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(deleteSql, name);
    }

    @GetMapping("/user/error-test")
    public void errorTest() {
        throw new IllegalArgumentException();
    }

    @GetMapping("/fruit")
    public Fruit getFruit() {
        return new Fruit("사과", 1000l);
    }
}
