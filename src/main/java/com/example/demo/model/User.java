package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    public User(String username) {
        this.username = username;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, length = 50)
    private String username;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL) //inverse side
    private Profile profile;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Order> orders = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Comment> comments = new LinkedHashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new LinkedHashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "group_members",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groups = new LinkedHashSet<>();


    public void addGroup(Group group) {
        groups.add(group);
        if (group.getUsers() == null) {
            group.setUsers(new HashSet<>());
        }
        group.getUsers().add(this);
    }

    public void addRole(Role role) {
        roles.add(role);
        if (role.getUsers() == null) {
            role.setUsers(new HashSet<>());
        }
        role.getUsers().add(this);
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.setUser(this);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setUser(this);
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
        if (profile != null) {
            profile.setUser(this);
        }
    }

    public void setAddress(Address address) {
        this.address = address;
        if (address != null) {
            address.setUser(this);
        }
    }
}

