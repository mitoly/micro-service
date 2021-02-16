package com.example.auth.core.entity;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {
    private Integer id;
    private String name;
    private String permissionUri;

    public Role(Integer id, String name, String permissionUri) {
        this.id = id;
        this.name = name;
        this.permissionUri = permissionUri;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermissionUri() {
        return permissionUri;
    }

    public void setPermissionUri(String permissionUri) {
        this.permissionUri = permissionUri;
    }

    @Override
    public String getAuthority() {
//        return getName();
        return getPermissionUri();
    }
}
