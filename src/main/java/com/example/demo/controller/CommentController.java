package com.example.demo.controller;

import com.example.demo.dto.CommentCreateRequest;
import com.example.demo.dto.CommentResponse;
import com.example.demo.model.Comment;
import com.example.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentResponse>> getComments(@RequestParam Long userId) {
        List<CommentResponse> comments = commentService.getCommentsByUserId(userId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> createComment(@RequestBody CommentCreateRequest comment,@PathVariable Long userId) {
        commentService.addComment(comment,userId);
        return ResponseEntity.noContent().build();
    }
}
