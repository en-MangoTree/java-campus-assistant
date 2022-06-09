package com.knockknock.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @CreateTime: 2022-05-25 14:41
 * @Description: 用户实体类
 */
@Data
public class User implements UserDetails {

    private Long id;

    private String userName;

    private String loginName;

    private String password;

    private String phone;

    private String wxNumber;

    private String email;

    private String university;

    private String openId;

    private String avatar;

    private String nickName;

    private boolean status;

    /**
     * 用户对应的角色表
     */
    private Role role;

    /**
     * 用户对应的菜单表
     */
    private List<Menu> menus;

    /**
     * 用户对应的权限表
     */
    private List<Permission> permissions;

    /**
     * 权限数据
     * @return
     */
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        if(role != null && role.getCode() != null) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getCode()));
        }
        if(permissions != null && permissions.size() > 0) {
            permissions.forEach(item ->{
                if(item.getCode() != null) {
                    list.add(new SimpleGrantedAuthority(item.getCode()));
                }
            });
        }
        return list;
    }

    /**
     * 获取用户名
     * @return
     */
    @Override
    @JsonIgnore
    public String getUsername() {
        return userName;
    }

    /**
     * 账号是否过期
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     * 账户是否锁定
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return false;
    }

    /**
     * 凭证是否过期
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /**
     * 是否启用
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return status;
    }
}
