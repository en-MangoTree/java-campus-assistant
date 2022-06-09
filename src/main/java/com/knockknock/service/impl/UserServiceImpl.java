package com.knockknock.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.knockknock.config.security.service.UserDetailsServiceImpl;
import com.knockknock.entity.Role;
import com.knockknock.entity.User;
import com.knockknock.mapper.UserMapper;
import com.knockknock.service.UserService;
import com.knockknock.utils.*;
import com.knockknock.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @CreateTime: 2022-05-25 16:24
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public Result login(LoginVo loginVo) {
//        开始登陆
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginVo.getUsername());
        System.out.println(userDetails);
//        判断用户是否存在，密码是否正确
        if(null == userDetails || !passwordEncoder.matches(MD5Util.MD5(loginVo.getPassword()), userDetails.getPassword())){
            return Result.fail("账户或密码错误，请重新输入");
        }
        if(!userDetails.isEnabled()){
            return Result.fail("该账号已禁用");
        }
//        登陆成功，在security对象中存入登陆信息
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        借助jwt来生成token，根据登陆信息获取token
        String token = tokenUtils.generateToken(userDetails);
        Map<String, String> map = new HashMap<>(2);
        map.put("tokenHead", tokenHead);
        map.put("token", token);
        return Result.success("登陆成功", map);
    }

    @Transactional
    @Override
    public Result insert(User user) {
        User existence = userMapper.findByUsername(user.getUsername());
        if( null != existence ) {
            return Result.fail("用户名已存在");
        }
        user.setPassword(passwordEncoder.encode(MD5Util.MD5(user.getPassword())));
        userMapper.insert(user);
        Role role = user.getRole();
        if(null != role) {
             userMapper.insertUserRole(user.getId(), role.getId());
        }
        return Result.success("用户添加成功！");
    }

    @Override
    public Result delete(Long id) {
        userMapper.delete(id);
        return Result.success("用户信息删除成功！");
    }

    @Transactional
    @Override
    public Result update(User user) {
        Role role = user.getRole();
        if(null != role) {
            userMapper.deleteUserRole(user.getId());
            userMapper.insertUserRole(user.getId(), role.getId());
        } else {
            userMapper.deleteUserRole(user.getId());
        }
        user.setPassword(passwordEncoder.encode(MD5Util.MD5(user.getPassword())));
        userMapper.update(user);
        return Result.success("修改用户信息成功！");
    }

    @Override
    public Result findPage(QueryInfo queryInfo) {
        PageHelper.startPage(queryInfo.getPageNumber(), queryInfo.getPageSize());
        Page<User> page = userMapper.findPage(queryInfo.getQueryString());
        long total = page.getTotal();
        List<User> result = page.getResult();
        result.forEach(item -> {
            item.setRole(userMapper.findRole(item.getId()));
            item.setLoginName(item.getUsername());
        });
        return PageResult.pageResult(total,result);
    }
}
