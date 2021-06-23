package com.experis.academystatisticsapp.repositories;

import com.experis.academystatisticsapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO User (email,password,is_admin) VALUES (?1,?2,?3)", nativeQuery = true)
    void createUser(String email, String password, byte isAdmin);

    @Query(value = "SELECT * FROM User user WHERE user.id = ?1", nativeQuery = true)
    User getUserById(Long id);

    @Query(value = "SELECT IF(EXISTS (SELECT * FROM User WHERE User.email =?1), 'true','false')", nativeQuery = true)
    boolean existsByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE User SET is_admin = ?2 WHERE User.id = ?1", nativeQuery = true)
    Integer updateIsAdmin(Long id,byte isAdmin);

    @Transactional
    @Modifying
    @Query(value = "UPDATE User SET email = ?1, is_admin = ?2 WHERE User.id = ?3", nativeQuery = true)
    User updateUserById();

    @Query(value = "DELETE FROM User WHERE User.id = ?1", nativeQuery = true)
    User deleteUserById();

    @Query(value = "SELECT * FROM User", nativeQuery = true)
    List<User> GetAll();

}
