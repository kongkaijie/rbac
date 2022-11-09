package com.example.demo.config;
import com.example.demo.entity.Permission;
import com.example.demo.entity.RolePermission;
import com.example.demo.entity.UserRole;
import com.example.demo.entity.Users;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.RolePermissionRepository;
import com.example.demo.repository.UserRoleRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    public UserRepository userRepository;
    public UserRoleRepository userRoleRepository;
    public RolePermissionRepository userRolePermissionRepository;
    public PermissionRepository permissionRepository;

    public CustomUserDetailsService(UserRepository userRepository, UserRoleRepository userRoleRepository, RolePermissionRepository userRolePermissionRepository,PermissionRepository permissionRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userRolePermissionRepository = userRolePermissionRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override//这一步只是获取到用户的信息，包装成security能认识的信息返回，security默认帮助做认证//尚未鉴权
    public UserDetails loadUserByUsername(String username) {
        //查询数据库
        //根据name找到对应user对象
        Users users = userRepository.findByName(username);
        int userId = users.getId();
        Set<UserRole> userRoles = userRoleRepository.findByUserId(userId);
        Set<Integer> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toSet());

        Set<RolePermission> rolePermissions = roleIds.stream().map(roleId -> userRolePermissionRepository.findByRoleId(roleId)).collect(Collectors.toSet());
        Set<Integer> permissionIds = rolePermissions.stream().map(RolePermission::getPermissionId).collect(Collectors.toSet());
//        Set<Optional<Permission>> permissions = permissionIds.stream().map(permissionId -> permissionRepository.findById(permissionId)).collect(Collectors.toSet());
        Set<Optional<Permission>> optionalPermissions = permissionIds.stream().map(permissionId -> permissionRepository.findById(permissionId)).collect(Collectors.toSet());
        Set<String> auths = optionalPermissions.stream().map(permission -> permission.map(Permission::getAuth).orElse(null)).collect(Collectors.toSet());
        //权限转换
        //查询user对象过查该对象数据库中的auths信息，返回SimpleGrantedAuthority类型对象的数组
        List<SimpleGrantedAuthority> authorityList = auths.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        System.out.println(authorityList);
//        将查询的信息封装到UserDetails并返回
        //这里只是给对应的对象增加权限
            return User
                    .withUsername(users.getName())
                    .password("777")
                    .authorities(authorityList)
                    .build();
    }
}
