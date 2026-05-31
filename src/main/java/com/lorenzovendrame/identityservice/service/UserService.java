package com.lorenzovendrame.identityservice.service;

import com.fasterxml.uuid.Generators;
import com.lorenzovendrame.identityservice.domain.User;
import com.lorenzovendrame.identityservice.repository.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User createUser(User user) {

        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        if (user.getAddress() != null) {
            UUID addressUuid = Generators.timeBasedEpochGenerator().generate();
            user.getAddress().setAddressId(addressUuid);
            userMapper.insertAddress(user.getAddress());
        }

        UUID userUuid = Generators.timeBasedEpochGenerator().generate();
        user.setUserId(userUuid);
        userMapper.insertUser(user);


        userMapper.insertUserRole(user.getUserId(), 1L); //Config inicial, alterar depois

        return userMapper.findById(user.getUserId()).orElseThrow();
    }

    @Transactional
    public User updateUserData(UUID userId, User userUpdates) {
        User existingUser = userMapper.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        existingUser.setName(userUpdates.getName());
        existingUser.setEmail(userUpdates.getEmail());
        existingUser.setPhoneNumber(userUpdates.getPhoneNumber());

        userMapper.updateUser(existingUser);

        if (userUpdates.getAddress() != null && existingUser.getAddress() != null) {
            userUpdates.getAddress().setAddressId(existingUser.getAddress().getAddressId());
            userMapper.updateAddress(userUpdates.getAddress());
        }

        return userMapper.findById(userId).orElseThrow();
    }

    @Transactional
    public void updateUserRoles(UUID userId, List<Long> roleIds) {

        userMapper.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


        userMapper.deleteUserRoles(userId);
        for (Long roleId : roleIds) {
            userMapper.insertUserRole(userId, roleId);
        }
    }
}
