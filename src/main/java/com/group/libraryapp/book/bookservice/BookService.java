package com.group.libraryapp.book.bookservice;


import com.group.libraryapp.book.Book;
import com.group.libraryapp.book.dto.BookCreateRequest;
import com.group.libraryapp.book.dto.BookLoanRequest;
import com.group.libraryapp.book.dto.BookReturnRequest;
import com.group.libraryapp.book.repository.BookRepository;
import com.group.libraryapp.controller.calculator.user.User;
import com.group.libraryapp.controller.calculator.user.UserRepository;
import com.group.libraryapp.user.loanhistory.UserLoanHistory;
import com.group.libraryapp.user.respository.UserLoanHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    private final UserLoanHistoryRepository userLoanHistoryRepository;

    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserLoanHistoryRepository userLoanHistoryRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveBook(BookCreateRequest request) {
        bookRepository.save(new Book(request.getName()));
    }

    @Transactional
    public void loanBook(BookLoanRequest request) {

        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalAccessError::new);

        // 추가된 로직, user_loan_history 를 확인해 책이 없으면 예외를 던짐
        // ( false 상태 == 대출되지 않은 상태 )
        if(userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)) {
            throw new IllegalArgumentException("이미 대출되어 있는 책입니다.");
        }

        // 책 대출 기록에 책 저장
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);

        /*
        System.out.println("=============================");
        System.out.println(user);
         */

        user.loanBook(book.getName());
    }

    // 책 반납 메서드
    @Transactional
    public void returnBook(BookReturnRequest request) {

        // 1. user 발견
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);

        // 2. 책 반납
        user.returnBook(request.getBookName());
    }


    @Transactional
    public void deleteUserHistory() {
        User user = userRepository.findByName("ABC")
                .orElseThrow(IllegalArgumentException::new);

        user.returnBook(user.getName());
    }
}
