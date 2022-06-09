package com.knockknock.config.security.service;

import com.knockknock.entity.Menu;
import com.knockknock.entity.Role;
import com.knockknock.entity.User;
import com.knockknock.mapper.UserMapper;
import com.knockknock.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @CreateTime: 2022-05-08 17:37
 * @Description: 实现自定义逻辑，重写方法
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        判断缓存中是否存在用户信息，有则中缓存中取，无则从数据库中取
        User user;
        if (redisUtils.hasKey("userInfo_" + userName)) {
            user = (User) redisUtils.getValue("userInfo_" + userName);
            redisUtils.expire("userInfo_" + userName, 5);
        } else {
//            mapper中自定义登陆
            user = userMapper.findByUsername(userName);
            if(null == user){
                throw new UsernameNotFoundException("用户名或密码错误");
            }
            Role role = userMapper.findRole(user.getId());
            if(role.getCode().equals("BOSS")){
                user.setRole(role);
                user.setPermissions(userMapper.findPermissions(null));
//                获取父级查单
                List<Menu> menus = userMapper.findMenus(null);
//                获取子级菜单
                menus.forEach(item-> item.setChildren(userMapper.findChildrenMenus(item.getId(), null)));
                user.setMenus(menus);
            }
            else {
//                非管理员查询角色信息
                user.setRole(userMapper.findRole(user.getId()));
                user.setPermissions(userMapper.findPermissions(user.getId()));
//                获取父级查单
                List<Menu> menus = userMapper.findMenus(user.getId());
//                获取子级菜单
                menus.forEach(item-> item.setChildren(userMapper.findChildrenMenus(item.getId(), user.getId())));
                user.setMenus(menus);
            }
            redisUtils.setValueTime("userInfo_" + userName, user, 5);
        }

        return user;
    }
}
