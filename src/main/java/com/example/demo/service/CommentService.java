package com.example.demo.service;

import com.example.demo.dto.CommentUpdateRequest;
import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }

    @Transactional
    void updateComment(Long id, CommentUpdateRequest commentUpdateRequest) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment == null) {
            return;
        } else {
            comment.setText(commentUpdateRequest.text());
            commentRepository.save(comment);
        }

    }

    Comment getCommentById(Long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        return comment;
    }
    List<Comment> getCommentsByUserId(Long userId) {
        return commentRepository.getCommentsByUserId(userId);
    }
}
