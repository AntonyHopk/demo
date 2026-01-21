package com.example.demo.service;

import com.example.demo.dto.UserCreateRequest;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    //Dependency injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        System.out.println("UserService init");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("UserService destroy");
    }

    public List<User> findAll() {
        return userRepository.getUsers();
    }

    public User findById(Long id) {
        return userRepository.getUserById(id).orElseThrow(() -> new RuntimeException("User not found"));

    }

    public User create(UserCreateRequest user) {
        return userRepository.Save(user.getUsername());
    }

    public User update(Long id, UserUpdateRequest user) {
        return userRepository.Update(id, user.getUsername());
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
