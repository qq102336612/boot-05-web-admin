package com.yz.admin.controller;

import com.yz.admin.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    /**
     * 来登录页
     * @return
     */
    @GetMapping(value = {"/","/login"})
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model){
        if (!StringUtils.isEmpty(user.getUserName()) && "123456".equals(user.getPassword())){
           //把登录成功的用户保存起来
            session.setAttribute("loginUser", user);
        }else {
            model.addAttribute("msg","账号密码错误");
            //回到登录页
            return "/login";
        }
        //登录成功后，防止表单重复提交。
        return "redirect:/main.html";
    }

    /**
     * 去main页面
     * @return
     */
    @GetMapping("/main.html")
    public String mainPage(HttpSession session,Model model){
        /*Object loginUser = session.getAttribute("loginUser");
        if (loginUser !=null){
            return "main";

        }else {
            model.addAttribute("msg","请重新登录");
            //回到登录页
            return "/login";

        }*/
        return "main";


    }
}
