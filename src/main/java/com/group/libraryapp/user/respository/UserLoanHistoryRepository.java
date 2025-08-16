package com.group.libraryapp.user.respository;

import com.group.libraryapp.user.loanhistory.UserLoanHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory,Long> {

    // 책 저장 메서드
    boolean existsByBookNameAndIsReturn(String bookName, boolean isReturn);

    Optional<UserLoanHistory> findByUserIdAndBookName(Long userId, String bookName);
}
