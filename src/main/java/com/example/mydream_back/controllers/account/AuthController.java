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

    @PostMapping("/signIn")
    public ResponseEntity<ReturnValue<Boolean>> signIn(@RequestBody String userid){
        ReturnValue<Boolean> returnValue = new ReturnValue<>();
        boolean isSigned = authService.checkIfSignedToday(userid);
        if(!isSigned){
            authService.signInToday(userid);
        }
        returnValue.isSuccess();
        returnValue.setRetValue(!isSigned);
        return ResponseEntity.ok(returnValue);
    }

    @GetMapping("/getSignInInfo")
    public ResponseEntity<ReturnValue<Map<String,Object>>> getSignInInfo(@RequestParam String userid){
        ReturnValue<Map<String,Object>> returnValue = new ReturnValue<>();
        Map<String,Object> map = authService.getSignInInfo(userid);
        returnValue.isSuccess();
        returnValue.setRetValue(map);
        return ResponseEntity.ok(returnValue);
    }

    @GetMapping("/getSignInDays")
    public ResponseEntity<ReturnValue<Integer>> getSignInDays(@RequestParam String userid){
        ReturnValue<Integer> returnValue = new ReturnValue<>();
        int count = authService.getConsecutiveSignInDays(userid);
        returnValue.isSuccess();
        returnValue.setRetValue(count);
        return ResponseEntity.ok(returnValue);
    }

    @GetMapping("/checkIsSigned")
    public ResponseEntity<ReturnValue<Boolean>> checkIsSigned(@RequestParam String userid){
        ReturnValue<Boolean> returnValue = new ReturnValue<>();
        boolean isSigned = authService.checkIfSignedToday(userid);
        returnValue.isSuccess();
        returnValue.setRetValue(isSigned);
        return ResponseEntity.ok(returnValue);
    }

}
