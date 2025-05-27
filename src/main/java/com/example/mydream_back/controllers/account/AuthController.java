package com.example.mydream_back.controllers.account;

import com.example.mydream_back.dto.ReturnValue;
import com.example.mydream_back.model.User;
import com.example.mydream_back.services.account.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/chickLogin")
    public ResponseEntity<ReturnValue<Boolean>> chickLogin(@RequestBody User user){
        ReturnValue<Boolean> returnValue = new ReturnValue<Boolean>();
        boolean isAuthenticated = authService.chickUserLogin(user);
        returnValue.isSuccess();
        returnValue.setRetValue(isAuthenticated);
        return ResponseEntity.ok(returnValue);
    }

    @PostMapping("/signIn")
    public ResponseEntity<ReturnValue> signIn(@RequestBody String userid){
        ReturnValue<Boolean> returnValue = new ReturnValue<>();
        authService.signInToday(userid);
        returnValue.isSuccess();
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
