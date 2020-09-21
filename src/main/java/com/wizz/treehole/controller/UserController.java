package com.wizz.treehole.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.wizz.treehole.commonUtils.R;
import com.wizz.treehole.config.MD5;
import com.wizz.treehole.entity.User;
import com.wizz.treehole.exceptionhandler.GuliException;
import com.wizz.treehole.mapper.UserMapper;
import com.wizz.treehole.service.UserService;
import com.wizz.treehole.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liqiqiorz
 * @since 2020-08-08
 */
@RestController
@RequestMapping("/treehole/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public R register(@RequestBody User user){
        String s = String.valueOf(System.currentTimeMillis());
        user.setSalt(s);
        String name = user.getName();
        String password = user.getPassword();
        user.setPassword(MD5.encrypt(password+s));
        userService.isExist(name);
        userService.save(user);
        return R.ok();
    }

    @GetMapping("/logout/{id}")
    public R logout(@PathVariable String id,HttpSession session){
        User user = userService.getById(id);
        if (user.getToken()==null){
            return R.error().message("该用户未登录..无法退出");
        }
        else {
            user.setToken(null);
            session.setAttribute("user",null);
            userMapper.keepTokenByIds(id);
            return R.ok().message("登出成功!");
        }
    }

    @PostMapping("/login")
    public R login(@RequestBody User user, HttpServletRequest request, HttpSession httpSession, HttpServletResponse response) throws UnsupportedEncodingException {
        User user2 = userService.queryUserByname(user.getName());

        HttpSession session = request.getSession();
        System.out.println("+++++++");
        System.out.println(session.getId()==httpSession.getId());
        if (user2==null){
            return R.error().message("用户不存在,请创造一个新用户!!!");
        }
        if (user2.getToken()!=null){
            httpSession.setAttribute("user",user2);
            return R.error().message("该用户已经登录");
        }
        user.setToken(session.getId());
        user2.setToken(session.getId());
        httpSession.setAttribute("user",user2);
        userMapper.keepTokenById(user2.getId(),user.getToken());

        User user1 = userMapper.queryUserByname(user.getName());
        String password = user.getPassword();

        String s=password+user1.getSalt();
        s= MD5.encrypt(s);

        User userLogin = userMapper.getByNameAndPassword(user.getName(),s);
        if (userLogin==null){
            throw new GuliException(20001,"用户名或密码错误!!!");
        }else {

//            登陆成功,返回token
            String tokens = JwtUtils.createToken(userLogin);
            userLogin.setTokens(tokens);
//            前端复选框要remember-me并且默认值为0,勾选值为1
            String remember=request.getParameter("remember-me");
//            设置cookie,cookie的name是user也可以写成remember,需要解码
            Cookie cookie=new Cookie("user", URLEncoder.encode(user.getName()+"-"+user.getPassword(),"UTF-8"));
            int maxAge="1".equals(remember)?24*60*60:0;
//            一天
            cookie.setMaxAge(maxAge);
            response.addCookie(cookie);
//            之后取cookie就是前端的操作
            return R.ok().data("user",userLogin);

        }
    }

}


