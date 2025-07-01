package com.example.mydream_back.controllers.account;

import com.example.mydream_back.dto.FileInfoDTO;
import com.example.mydream_back.dto.ReturnValue;
import com.example.mydream_back.dto.UserDTO;
import com.example.mydream_back.dto.UserInfo;
import com.example.mydream_back.model.FileInfo;
import com.example.mydream_back.model.User;
import com.example.mydream_back.services.account.UserService;
import com.example.mydream_back.utils.FileHelper;
import com.example.mydream_back.utils.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @GetMapping("/getUserInfo")
    public ResponseEntity<ReturnValue> getUserInfo(@RequestParam String user_id) {
        ReturnValue<UserInfo> returnValue = new ReturnValue<>();
        UserInfo userInfo = userService.getUserInfoByUserId(user_id);
        returnValue.setRetValue(userInfo);
        returnValue.isSuccess();
        return ResponseEntity.ok(returnValue);
    }

    @PostMapping("/updateUserInfo")
    public ResponseEntity<ReturnValue> updateUserInfo(@RequestBody UserInfo userInfo){
        ReturnValue returnValue = new ReturnValue();
        userService.updateUserInfo(userInfo);
        returnValue.isSuccess();
        return ResponseEntity.ok(returnValue);
    }

    @PostMapping("/updateUserImg")
    public ResponseEntity<ReturnValue> updateUserImg(
            @RequestParam MultipartFile file,
            @RequestParam String user_id) {
        ReturnValue returnValue = new ReturnValue();
        try {
            FileInfo fileInfo = FileHelper.uploadFile(file, "userimg/" + user_id);

            // 设置文件信息
            FileInfoDTO fileInfoDTO = new FileInfoDTO();
            fileInfoDTO.setFile_path("/userimg/" + user_id + "/");
            fileInfoDTO.setFile_name(fileInfo.getStoredName());
            fileInfoDTO.setFile_type(10001);
            UserDTO user = new UserDTO();
            user.setUser_id(user_id);
            user.setUser_img(fileInfoDTO);

            Boolean isNoHaveImg = StringHelper.isEmpty(user.getUser_img().getFile_url());
            if (isNoHaveImg) {
                userService.InsertUserFile(user);
            } else {
                userService.updateUserFile(user);
            }

            returnValue.isSuccess();
            return ResponseEntity.ok(returnValue);
        } catch (Exception e) {
            e.printStackTrace();
            returnValue.isError();
            return ResponseEntity.ok(returnValue);
        }
    }

}
