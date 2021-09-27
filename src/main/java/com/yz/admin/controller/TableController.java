package com.yz.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yz.admin.bean.User;
import com.yz.admin.exception.UserTooManyException;
import com.yz.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class TableController {
    @Autowired
    UserService userService;

    @GetMapping("/basic_table")
    public String basic_table(){
        return "table/basic_table";

    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        userService.removeById(id);
        return "redirect:/dynamic_table";
    }
    @ResponseBody
    @PostMapping("/userById")
    public User userById(Integer id){
        User byId = userService.getById(id);
        return byId;
    }
    @GetMapping("/dynamic_table")
    public String dynamic_table(/*@RequestParam(value = "pn",defaultValue = "1") Integer pn,*/Model model){
        //表格内容遍历
        /*List<User> users = Arrays.asList(new User("zhangsan", "123456"),
                new User("lisi", "123456"),
                new User("haha", "123456"),
                new User("hehe", "123456"));
        model.addAttribute("users",users);
        if (users.size()>3){
            throw new UserTooManyException();
        }*/

        List<User> list = userService.list();
        model.addAttribute("users",list);
        //分页查询数据

        /*Page<User> userPage = new Page<>(pn, 2);//第一个参数是当前页数，第二个参数是每页显示的条数
        Page<User> page = userService.page(userPage, null);//第二个参数代表查询条件
        model.addAttribute("page",page);*/
        return "table/dynamic_table";
    }

    @GetMapping("/responsive_table")
    public String responsive_table(){
        return "table/responsive_table";
    }

    @GetMapping("/editable_table")
    public  String editable_table(){
        return "table/editable_table";
    }


}
