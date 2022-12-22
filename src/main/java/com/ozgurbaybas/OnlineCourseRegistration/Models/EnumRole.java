package com.ozgurbaybas.OnlineCourseRegistration.Models;

public enum EnumRole {
    ROLE_USER("user"),
    ROLE_ADMIN("admin"),
    ROLE_DEAN("dean"),
    ROLE_INSTRUCTOR("instructor"),
    ROLE_STUDENT("student");

    public final String roleName;

    EnumRole(String name) {
        this.roleName = name;
    }

    public static EnumRole getRole(String name) {
        for (EnumRole enumRole : EnumRole.values()) {
            if (enumRole.roleName.equals(name)) {
                return enumRole;
            }
        }
        throw new RuntimeException("Role is not found.");
    }
    public String getRoleName() {
        return roleName;
    }
}
