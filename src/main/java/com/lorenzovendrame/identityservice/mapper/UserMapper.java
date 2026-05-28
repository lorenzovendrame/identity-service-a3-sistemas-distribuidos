package com.lorenzovendrame.identityservice.mapper;

import com.lorenzovendrame.identityservice.domain.Address;
import com.lorenzovendrame.identityservice.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.Optional;
import java.util.UUID;

@Mapper
public interface UserMapper {
    void insertAddress(Address address);
    void insertUser(User user);
    void insertUserRole(@Param("userId") UUID userId, @Param("roleId") Long roleId);

    void updateUser(User user);
    void updateAddress(Address address);

    void deleteUserRoles(UUID userId);

    // Consulta (Mapeada no XML por conta dos JOINS complexos)
    Optional<User> findById(UUID userId);
}