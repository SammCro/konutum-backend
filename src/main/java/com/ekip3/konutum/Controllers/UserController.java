package com.ekip3.konutum.Controllers;

import com.ekip3.konutum.Entities.User;
import com.ekip3.konutum.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

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
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/getUser")
    public ResponseEntity<?> getUser(@RequestParam("email") String email){
        return ResponseEntity.ok(userService.getUser(email));
    }

    @GetMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestParam("email") String email){
        userService.deleteUser(email);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getUserFavorites/{id}")
    public ResponseEntity<?> getUserFavorites(@PathVariable Long id){
        return ResponseEntity.ok(userService.getFavoriteHouses(id));
    }

    @PostMapping("/addFavoriteHouse/{id}/{houseId}")
    public ResponseEntity<?> addFavoriteHouse(@PathVariable Long id, @PathVariable Long houseId){
        User user = userService.getUser(id);
        String favoriteHouses = user.getIdOfFavoriteHouses();
        favoriteHouses += "," + houseId;
        user.setIdOfFavoriteHouses(favoriteHouses);
        userService.updateUser(user);
        List<Long> favoriteHousesList = userService.getFavoriteHouses(id);
        return ResponseEntity.ok(favoriteHousesList);
    }
}
