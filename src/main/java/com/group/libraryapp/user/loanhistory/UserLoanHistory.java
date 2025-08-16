package com.group.libraryapp.user.loanhistory;


import com.group.libraryapp.controller.calculator.user.User;

import javax.persistence.*;

@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String bookName;
    private boolean isReturn;

    public UserLoanHistory() {
    }

   public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = true;
   }


    // 책 반납 상태로 변경
    public void doReturn() {

        this.isReturn = true;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getBookName() {
        return bookName;
    }

    public boolean isReturn() {
        return isReturn;
    }
}
