package com.example.mydream_back.controllers.account;

import com.example.mydream_back.dto.ReturnValue;
import com.example.mydream_back.dto.UserDTO;
import com.example.mydream_back.model.User;
import com.example.mydream_back.services.account.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/account/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/chickLogin")
    public ResponseEntity<ReturnValue<UserDTO>> chickLogin(@RequestBody User user){
        ReturnValue<UserDTO> returnValue = new ReturnValue<UserDTO>();
        UserDTO userDTO = authService.chickUserLogin(user);
        if( userDTO!=null ){
            returnValue.isSuccess();
            returnValue.setRetValue(userDTO);
        }else{
            returnValue.isFail();
        }
        return ResponseEntity.ok(returnValue);
    }

    @GetMapping("/chickUsernameIsHave")
    public ResponseEntity<ReturnValue<Boolean>> chickUsernameIsHave(@RequestParam String user_name){
        ReturnValue<Boolean> returnValue = new ReturnValue<Boolean>();
        int count = authService.getUserCountByUsername(user_name);
        returnValue.isSuccess();
        if( count>0 ){
            returnValue.setRetValue(true);
        }else{
            returnValue.setRetValue(false);
        }
        return ResponseEntity.ok(returnValue);
    }

}
