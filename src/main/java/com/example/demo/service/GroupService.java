package com.example.demo.service;

import com.example.demo.dto.AddToGroupRequest;
import com.example.demo.dto.GroupCreateRequest;
import com.example.demo.dto.GroupResponse;
import com.example.demo.dto.UserShortResponse;
import com.example.demo.mapper.DTOMapper;
import com.example.demo.model.Group;
import com.example.demo.model.User;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public void createGroup(GroupCreateRequest groupCreateRequest) {
        groupRepository.save(new Group(groupCreateRequest.name()));
    }

    public List<GroupResponse> findAllGroups() {
        List<Group> groups = groupRepository.findAll();
        return groups.stream().map(DTOMapper::toGroupResponse).collect(Collectors.toList());
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    @Transactional
    public void addUserToGroup(Long id, AddToGroupRequest addToGroupRequest) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Group not found"));
        User user = userRepository.findById(addToGroupRequest.userId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (group.getUsers().contains(user)) {
            return;
        } else {
            group.getUsers().add(user);
        }
        user.addGroup(group);
        groupRepository.save(group);

    }

    @Transactional
    public void removeUserFromGroup(Long id, AddToGroupRequest addToGroupRequest) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Group not found"));
        User user = userRepository.findById(addToGroupRequest.userId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        group.getUsers().remove(user);
        group.removeUser(user);
        groupRepository.save(group);
    }

    public void renameGroup(Long id, String newName) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Group not found"));
        group.setName(newName);

        groupRepository.save(group);
    }

}
