package com.ekip3.konutum.Controllers;

import com.ekip3.konutum.Entities.User;
import com.ekip3.konutum.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.ws.Response;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam("email") String email, @RequestParam("password") String password){
        if (userService.isUserExist(email, password)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/register")
    public ResponseEntity<?> register(@RequestParam("email") String email, @RequestParam("password") String password){
        if (userService.isUserExist(email, password)){
            return ResponseEntity.badRequest().build();
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
