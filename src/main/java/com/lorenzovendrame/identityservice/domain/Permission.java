package com.lorenzovendrame.identityservice.domain;

import java.util.Objects;

public class Permission {
    private Long permissionId;
    private String permissionName;
    private String permissionDescription;

    public Permission(){}

    public Long getPermissionId() {
        return permissionId;
    }
    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionDescription() {
        return permissionDescription;
    }
    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return Objects.equals(permissionId, that.permissionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permissionId);
    }

    @Override
    public String toString() {
        return "Permission {\n" +
                "  permissionId = " + permissionId + ",\n" +
                "  permissionName= '" + permissionName + ",\n" +
                "  permissionDescription = '" + permissionDescription + ",\n" +
                '}';
    }
}