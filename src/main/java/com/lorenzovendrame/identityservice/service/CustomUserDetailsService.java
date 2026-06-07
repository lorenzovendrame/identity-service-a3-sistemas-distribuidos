package com.lorenzovendrame.identityservice.service;

import com.lorenzovendrame.identityservice.domain.Role;
import com.lorenzovendrame.identityservice.domain.User;
import com.lorenzovendrame.identityservice.repository.UserMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    public CustomUserDetailsService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userMapper.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email));

        List<Role> roles = user.getRoles() != null ? user.getRoles() : Collections.emptyList();

        List<SimpleGrantedAuthority> authorities = roles.stream()
                .filter(role -> role != null && role.getRoleName() != null)
                .map(role -> {
                    String name = role.getRoleName().toUpperCase();
                    // 💡 Garante o prefixo ROLE_ exigido pelo hasRole() do Spring Security
                    if (!name.startsWith("ROLE_")) {
                        name = "ROLE_" + name;
                    }
                    return new SimpleGrantedAuthority(name);
                })
                .collect(Collectors.toList());

        return new CustomUserDetails(user.getUserId(), user.getEmail(), user.getPassword(), authorities);
    }
}