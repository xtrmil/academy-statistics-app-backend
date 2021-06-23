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
    @Query(value = "INSERT INTO User (email,first_name, last_name, password,is_admin) VALUES (?1,?2,?3, ?4, ?5)", nativeQuery = true)
    void createUser(String email, String firstName, String lastName, String password, byte isAdmin);

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
    @Query(value = "UPDATE User SET email = ?2, first_name = ?3, last_name = ?4 " +
            "WHERE User.id = ?1", nativeQuery = true)
    void updateUserById(Long id, String email, String firstName, String lastName);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM User WHERE User.id = ?1", nativeQuery = true)
    void deleteUserById(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE User SET password = ?2 WHERE User.id = ?1", nativeQuery = true)
    void updatePasswordByUserId(Long id, String password);

    @Query(value = "SELECT * FROM User", nativeQuery = true)
    List<User> GetAll();

}
