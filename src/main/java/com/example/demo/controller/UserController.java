package com.example.demo.controller;

import com.example.demo.entity.Users;
import com.example.demo.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("task")
public class UserController {

    private static final Set<String> NAME_EXCEPTED_AUTHORITIES_ANY = Set.of("admin111");
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<Users> getManUser(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication(authentication,NAME_EXCEPTED_AUTHORITIES_ANY)){
            return userService.getAgendManUsers();
//        }
//        throw new RuntimeException();
//    }
//如果不在configure里面认证，可以放在controller鉴权
//    private boolean authentication(Authentication authentication, Set<String> expectedAuthoritiesAny) {
//        return authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .anyMatch(expectedAuthoritiesAny::contains);
    }

}
