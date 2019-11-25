package edu.udacity.java.nano.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
public class LoginController {

    /**
     * Login Page
     */
    @GetMapping("/")
    public ModelAndView login() {
        return new ModelAndView("/login");
    }

    /**
     * Chatroom Page
     */
    @PostMapping("/index")
    public ModelAndView index(@RequestParam String username, HttpServletRequest request) throws UnknownHostException {
        System.out.println(String.format("Logging in by username [%s]", username));
        if (username == null || username.isEmpty()) username = "GUEST"+System.currentTimeMillis();
        ModelAndView chatMav = new ModelAndView("chat");
        chatMav.addObject("username", username);
        chatMav.addObject("webSocketUrl", String.format("ws://%s:%s%s/chat/%s", InetAddress.getLocalHost().getHostAddress(), request.getServerPort(), request.getContextPath(), username));
        return chatMav;
    }

}
