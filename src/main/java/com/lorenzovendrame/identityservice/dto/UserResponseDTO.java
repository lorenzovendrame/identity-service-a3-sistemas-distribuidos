package com.lorenzovendrame.identityservice.dto;

import com.lorenzovendrame.identityservice.domain.User;
import java.util.UUID;

public class UserResponseDTO {
    private UUID userId;
    private String name;
    private String email;
    private String phoneNumber;

    public UserResponseDTO() {}

    public UserResponseDTO(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
    }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
