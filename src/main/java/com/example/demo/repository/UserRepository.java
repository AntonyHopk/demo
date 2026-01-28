package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Spring data query methods
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByUsernameIgnoreCase(String username);

    //Native query
    @Query(
            value = "select * from users where username = :username",
            nativeQuery = true
    )
    Optional<User> findNative(@Param("username") String username);

    //jpql(jpa query language)
    @Query("select u from User u where u.username= :username")
    Optional<User> findJpql(@Param("username") String username);
}
