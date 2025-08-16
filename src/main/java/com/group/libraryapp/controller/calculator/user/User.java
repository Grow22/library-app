package com.group.libraryapp.controller.calculator.user;


import com.group.libraryapp.user.loanhistory.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id = null;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();
    @Column(nullable = false, length = 20)
    private String name;

    private Integer age;
    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void updateName(String name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }

    public void loanBook(String bookName) {
        this.userLoanHistories.add(new UserLoanHistory(this, bookName));
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void returnBook(String bookName) {

        // 1. bookName 에 해당하는 책을 find
        UserLoanHistory targetHistory = this.userLoanHistories.stream()
                .filter(history -> history.getBookName().equals(bookName))
                .findFirst().orElseThrow(IllegalArgumentException::new);
        // find 한 책을 반납 처리
        targetHistory.doReturn();
    }
}
