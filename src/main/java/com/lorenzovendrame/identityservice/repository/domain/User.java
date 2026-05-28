package com.lorenzovendrame.identityservice.repository.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class User {
    private UUID userId;
    private String name;
    private String cpf;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String password;
    private LocalDateTime createdAt;

    private Address address;
    private List<Role> roles;

    public User() {}

    public UUID getUserId(){
        return userId;
    }
    public void setUserId(UUID userId){
        this.userId = userId;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getCpf(){
        return cpf;
    }
    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public String getEmail(){
        return email;}
    public void setEmail(String email){
        this.email = email;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth(){
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }

    public Address getAddress()
    { return address;
    }
    public void setAddress(Address address){
        this.address = address;
    }

    public List<Role> getRoles(){
        return roles;
    }
    public void setRoles(List<Role> roles){
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}