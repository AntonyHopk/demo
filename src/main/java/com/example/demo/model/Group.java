package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "groups")
public class Group {

    public Group(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY)
    private Set<User> users;

    public void removeUser(User user) {
        users.remove(user);
        if (user.getGroups() == null) {
            user.setGroups(new HashSet<>());
        }
        user.getGroups().add(this);
    }
}
