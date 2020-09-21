package com.wizz.treehole.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wizz.treehole.entity.User;
import com.wizz.treehole.exceptionhandler.GuliException;
import com.wizz.treehole.mapper.UserMapper;
import com.wizz.treehole.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liqiqiorz
 * @since 2020-08-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User queryUserByname(String name) {
        return baseMapper.queryUserByname(name);
    }
//判断用户民是否被使用
    @Override
    public void isExist(String name) {
        User user = userMapper.queryUserByname(name);
        if (user!=null){
            throw new GuliException(20001,"用户名已存在");
        }
    }

    @Override
    public List<User> searchByName(String name) {

        List<User> userList = baseMapper.selectList(new QueryWrapper<User>().like("name", name));
        return userList;
    }

    @Override
    public User getUserByToken(String token) {
        return userMapper.getUserByToken(token);
    }

    @Override
    public List<User> orgetList() {

        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("id",1)
                .or()
                .between("id",3,6);
        List<User> users = userMapper.selectList(wrapper);
        return users;
    }

//    @Override
//    public List<Review> selectAllReview(String id) {
//
//        List<Review> uid = baseMapper.selectList(new QueryWrapper<Review>().eq("uid", id));
//    }
}
