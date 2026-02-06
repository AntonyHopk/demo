package com.example.demo.service;

import com.example.demo.dto.CommentCreateRequest;
import com.example.demo.dto.CommentResponse;
import com.example.demo.dto.CommentUpdateRequest;
import com.example.demo.mapper.DTOMapper;
import com.example.demo.model.Comment;
import com.example.demo.model.User;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public void addComment(CommentCreateRequest commentCreateRequest,Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("User not found"));
        Comment comment = DTOMapper.toComment(commentCreateRequest);
        user.addComment(comment);
        commentRepository.save(comment);
    }

    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }

    @Transactional
   public  void updateComment(Long id, CommentUpdateRequest commentUpdateRequest) {
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

   public List<CommentResponse> getCommentsByUserId(Long userId) {
        List<Comment> comments = commentRepository.getCommentsByUserId(userId);
        return comments.stream().map(DTOMapper::toCommentResponse).toList();
    }
}
