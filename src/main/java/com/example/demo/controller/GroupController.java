package com.example.demo.controller;

import com.example.demo.dto.AddToGroupRequest;
import com.example.demo.dto.GroupCreateRequest;
import com.example.demo.dto.GroupResponse;
import com.example.demo.model.Group;
import com.example.demo.repository.GroupRepository;
import com.example.demo.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @GetMapping
    public List<GroupResponse> findAll() {
        return groupService.findAllGroups();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody GroupCreateRequest group) {
        groupService.createGroup(group);
        return ResponseEntity.created(URI.create("/api/groups"+group.name())).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> addUserToGroup(@PathVariable Long id, @RequestBody AddToGroupRequest group) {
        groupService.addUserToGroup(id, group);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> removeUserFromGroup(@PathVariable Long id, @RequestBody AddToGroupRequest group) {
        groupService.removeUserFromGroup(id, group);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/rename/{id}")
    public ResponseEntity<?> renameGroup(@PathVariable Long id, @RequestBody String newName) {
        groupService.renameGroup(id, newName);
        return ResponseEntity.ok().build();
    }
}
