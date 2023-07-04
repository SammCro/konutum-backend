package com.ekip3.konutum.Repositories;

import com.ekip3.konutum.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    boolean existsByEmailAndPassword(String email, String password);

    void deleteByEmail(String email);

    // find the usertype with email and password
    @Query(value = "SELECT usertype FROM user WHERE email = ?1 AND password = ?2", nativeQuery = true)
    String findUserType(String email, String password);
}
