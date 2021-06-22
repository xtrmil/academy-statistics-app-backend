package com.experis.academystatisticsapp.repositories;

import com.experis.academystatisticsapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.PreparedStatement;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    boolean existsByEmail(String email);

//    @Query(value = "INSERT INTO User VALUES (email = ?1, password = ?2)", nativeQuery = true)
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO User (email,password,is_admin) VALUES (?1,?2,?3)", nativeQuery = true)
    void addUser(String email, String password, byte isAdmin);


    @Query(value = "SELECT * FROM User user WHERE user.id = ?1", nativeQuery = true)
    User getUserById(Long id);

    @Query(value = "SELECT IF(EXISTS (SELECT * FROM User user WHERE user.email =?1), 'true','false')", nativeQuery = true)
    boolean existsByEmail(String email);

    //updateUser();
    //deleteUser();

}
