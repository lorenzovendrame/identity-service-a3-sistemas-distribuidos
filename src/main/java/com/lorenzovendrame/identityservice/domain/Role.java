package com.lorenzovendrame.identityservice.domain;

import java.util.List;
import java.util.Objects;

public class Role {
    private Long roleId;
    private String roleName;
    private String description;
    private List<Permission> permissions;

    public Role() {}

    public Long getRoleId() {
        return roleId;
    }
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }
    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(roleId, role.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId);
    }

    @Override
    public String toString() {
        return "Role {\n" +
                "  roleId = " + roleId + ",\n" +
                "  roleName = '" + roleName + "',\n" +
                "  permissions = " + permissions + "\n" +
                "}";
    }
}