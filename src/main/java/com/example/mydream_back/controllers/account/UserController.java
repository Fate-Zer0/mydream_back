package com.example.mydream_back.controllers.account;

import com.example.mydream_back.dto.ReturnValue;
import com.example.mydream_back.services.account.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/account/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signIn")
    public ResponseEntity<ReturnValue<Boolean>> signIn(@RequestBody String user_id){
        ReturnValue<Boolean> returnValue = new ReturnValue<>();
        boolean isSigned = userService.checkIfSignedToday(user_id);
        if(!isSigned){
            userService.signInToday(user_id);
        }
        returnValue.isSuccess();
        returnValue.setRetValue(!isSigned);
        return ResponseEntity.ok(returnValue);
    }

    @GetMapping("/getSignInInfo")
    public ResponseEntity<ReturnValue<Map<String,Object>>> getSignInInfo(@RequestParam String user_id){
        ReturnValue<Map<String,Object>> returnValue = new ReturnValue<>();
        Map<String,Object> map = userService.getSignInInfo(user_id);
        returnValue.isSuccess();
        returnValue.setRetValue(map);
        return ResponseEntity.ok(returnValue);
    }

    @GetMapping("/getSignInDays")
    public ResponseEntity<ReturnValue<Integer>> getSignInDays(@RequestParam String user_id){
        ReturnValue<Integer> returnValue = new ReturnValue<>();
        int count = userService.getConsecutiveSignInDays(user_id);
        returnValue.isSuccess();
        returnValue.setRetValue(count);
        return ResponseEntity.ok(returnValue);
    }

    @GetMapping("/checkIsSigned")
    public ResponseEntity<ReturnValue<Boolean>> checkIsSigned(@RequestParam String user_id){
        ReturnValue<Boolean> returnValue = new ReturnValue<>();
        boolean isSigned = userService.checkIfSignedToday(user_id);
        returnValue.isSuccess();
        returnValue.setRetValue(isSigned);
        return ResponseEntity.ok(returnValue);
    }

    @GetMapping("/getSignInDates")
    public ResponseEntity<ReturnValue<List<String>>> getSignInDates(@RequestParam String user_id, @RequestParam int year, @RequestParam int month){
        ReturnValue<List<String>> returnValue = new ReturnValue<>();
        List<String> signInDates = userService.getSignInDatesByYearAndMonth(user_id, year, month);
        returnValue.isSuccess();
        returnValue.setRetValue(signInDates);
        return ResponseEntity.ok(returnValue);
    }

}
