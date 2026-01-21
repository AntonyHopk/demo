package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {
    private final List<User> users = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);

    public List<User> getUsers() {
        return users;
    }

    public Optional<User> getUserById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    public User Save(String username) {
        User user = new User(counter.incrementAndGet(), username);
        users.add(user);
        return user;
    }

    public User Update(Long id, String username) {
        User user = getUserById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(username);
        return user;

    }
    public void deleteById(Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }
}
