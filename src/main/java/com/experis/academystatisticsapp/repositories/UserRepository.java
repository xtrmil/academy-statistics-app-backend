package com.experis.academystatisticsapp.repositories;

import com.experis.academystatisticsapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //@Modifying
    @Query(value = "INSERT INTO User", nativeQuery = true)
    User addUser(String email, String password);

    @Query(value = "SELECT user FROM User user WHERE user.id = ?1")
    User getUserById(Long id);

    @Query(value = "SELECT user FROM User user WHERE user.email =?1")
    boolean existsByEmail(String email);

    //updateUser();
    //deleteUser();

}
