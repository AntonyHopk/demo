package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.Set;


public record UserCreateRequest (ProfileRequest profile,String username, Set<String> roles){

}
