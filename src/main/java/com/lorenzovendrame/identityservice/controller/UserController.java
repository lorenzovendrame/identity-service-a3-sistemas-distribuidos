package com.lorenzovendrame.identityservice.controller;

import com.lorenzovendrame.identityservice.domain.User;
import com.lorenzovendrame.identityservice.dto.UserResponseDTO;
import com.lorenzovendrame.identityservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);

        UserResponseDTO responseView = new UserResponseDTO(createdUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseView);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable UUID id, @RequestBody User user) {
        User updated = userService.updateUserData(id, user);

        UserResponseDTO responseView = new UserResponseDTO(updated);

        return ResponseEntity.status(HttpStatus.OK).body(responseView);
    }

    @PutMapping("/{id}/roles")
    public ResponseEntity<String> changeRoles(@PathVariable UUID id, @RequestBody List<Long> roleIds) {
        userService.updateUserRoles(id, roleIds);
        return ResponseEntity.ok("Papéis do usuário atualizados com sucesso.");
    }

    @GetMapping("/get")
    public ResponseEntity<UserResponseDTO> getUser(@RequestParam UUID id){
        User user = userService.getUser(id);

        UserResponseDTO userResponse = new UserResponseDTO(user, id);

        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }
}