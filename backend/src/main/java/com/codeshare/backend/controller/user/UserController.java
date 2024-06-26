//package com.codeshare.backend.controller.user;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.codeshare.backend.mapper.UserMapper;
//import com.codeshare.backend.pojo.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//public class UserController {
//    @Autowired
//    UserMapper userMapper;
//
//    @GetMapping("/user/all/")
//    public List<User> getAll() {
//        return userMapper.selectList( null );
//    }
//
//    @GetMapping("/user/{userId}/")
//    public User getuser(@PathVariable int userId) {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("id", userId);
//        return userMapper.selectById(userId);
//    }
//
//    @GetMapping("/user/add/{userId}/{username}/{password}/")
//    public String addUser(
//            @PathVariable int userId,
//            @PathVariable String username,
//            @PathVariable String password) {
//        if(password.length()<6) {
//            return "密码太短，请输入6位及以上密码";
//        }
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodePassword = passwordEncoder.encode(password);
//        User user = new User(userId, username, encodePassword);
//        userMapper.insert(user);
//        return "Add User Successfully";
//    }
//
//    @GetMapping("/user/delete/{userId}/")
//    public String deleteUser(@PathVariable int userId) {
//        userMapper.deleteById(userId);
//        return "Delete User Successfully";
//    }
//
//
//
////    返回编号在2~3的所有用户
////    @GetMapping("/user/{userId}/")
////    public List<User> getuserlist(@PathVariable int userId) {
////        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
////        queryWrapper.ge("id", 2).le("id", 3);
////        return userMapper.selectList(queryWrapper);
////    }
//
//}
