package com.example.mydream_back.controllers.account;

import com.example.mydream_back.dto.ReturnValue;
import com.example.mydream_back.services.account.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/account/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signIn")
    public ResponseEntity<ReturnValue<Boolean>> signIn(@RequestBody String userid){
        ReturnValue<Boolean> returnValue = new ReturnValue<>();
        boolean isSigned = userService.checkIfSignedToday(userid);
        if(!isSigned){
            userService.signInToday(userid);
        }
        returnValue.isSuccess();
        returnValue.setRetValue(!isSigned);
        return ResponseEntity.ok(returnValue);
    }

    @GetMapping("/getSignInInfo")
    public ResponseEntity<ReturnValue<Map<String,Object>>> getSignInInfo(@RequestParam String userid){
        ReturnValue<Map<String,Object>> returnValue = new ReturnValue<>();
        Map<String,Object> map = userService.getSignInInfo(userid);
        returnValue.isSuccess();
        returnValue.setRetValue(map);
        return ResponseEntity.ok(returnValue);
    }

    @GetMapping("/getSignInDays")
    public ResponseEntity<ReturnValue<Integer>> getSignInDays(@RequestParam String userid){
        ReturnValue<Integer> returnValue = new ReturnValue<>();
        int count = userService.getConsecutiveSignInDays(userid);
        returnValue.isSuccess();
        returnValue.setRetValue(count);
        return ResponseEntity.ok(returnValue);
    }

    @GetMapping("/checkIsSigned")
    public ResponseEntity<ReturnValue<Boolean>> checkIsSigned(@RequestParam String userid){
        ReturnValue<Boolean> returnValue = new ReturnValue<>();
        boolean isSigned = userService.checkIfSignedToday(userid);
        returnValue.isSuccess();
        returnValue.setRetValue(isSigned);
        return ResponseEntity.ok(returnValue);
    }

}
