package com.example.mydream_back.controllers.account;

import com.example.mydream_back.dto.ReturnValue;
import com.example.mydream_back.dto.UserDTO;
import com.example.mydream_back.model.User;
import com.example.mydream_back.services.account.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;

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

    @PostMapping("signUp")
    public ReturnValue signUp(@RequestBody User user) {
        ReturnValue<UserDTO> ret = new ReturnValue<>();

        int count = authService.getUserCountByUsername(user.getUser_name());
        if(count > 0){
            ret.isFail();
            ret.setRetDesc("用户名已存在!");
            return ret;
        }
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setCreatetime(sdf.format(System.currentTimeMillis()));
        int i = authService.signUp(user);
        if(i > 0){
            UserDTO userDTO = authService.chickUserLogin(user);
            ret.setRetValue(userDTO);
            ret.isSuccess();
        }else{
            ret.isFail();
            ret.setRetDesc("添加失败,请确认用户信息!");
        }
        return ret;
    }

}
