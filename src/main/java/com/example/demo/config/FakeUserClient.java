package com.example.demo.config;

import com.example.demo.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FakeUserClient {
    private static List<UserDto> users = List.of(
            new UserDto("小明","123", List.of("ROLE_ADMIN")),
            new UserDto("小红","123",List.of("ROLE_NO"))
    );

    public Optional<UserDto> findByUsername(String username){
        return users.stream().filter(user-> user.getName().equals(username)).findFirst();
    }
}
