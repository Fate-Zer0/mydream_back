package com.example.mydream_back.controllers.account;

import com.example.mydream_back.dto.UserDTO;
import com.example.mydream_back.model.User;
import com.example.mydream_back.services.account.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/chickLogin")
    public ResponseEntity<Boolean> chickLogin(@RequestBody User user){
        boolean isAuthenticated = authService.chickUserLogin(user);
        return new ResponseEntity<>(isAuthenticated, HttpStatus.OK);
    }

}
