package com.ekip3.konutum.Repositories;

import com.ekip3.konutum.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    boolean existsByEmailAndPassword(String email, String password);

    void deleteByEmail(String email);
}
