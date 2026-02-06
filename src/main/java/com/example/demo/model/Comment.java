package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Table(name = "comments")
@RequiredArgsConstructor
public class Comment {

    public Comment(String text) {
        this.text = text;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text", length = 100, nullable = false)
    private String text;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
}
