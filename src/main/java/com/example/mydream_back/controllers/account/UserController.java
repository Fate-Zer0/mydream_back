package com.example.mydream_back.controllers.account;

import com.example.mydream_back.dto.*;
import com.example.mydream_back.model.FileInfo;
import com.example.mydream_back.services.account.UserService;
import com.example.mydream_back.utils.FileHelper;
import com.example.mydream_back.utils.StringHelper;
import com.example.mydream_back.utils.TimeCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<ReturnValue<FileInfoDTO>> updateUserImg(
            @RequestParam MultipartFile file,
            @RequestParam String user_id) {
        ReturnValue<FileInfoDTO> returnValue = new ReturnValue<>();
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

            returnValue.setRetValue(fileInfoDTO);
            returnValue.isSuccess();
            return ResponseEntity.ok(returnValue);
        } catch (Exception e) {
            e.printStackTrace();
            returnValue.isError();
            return ResponseEntity.ok(returnValue);
        }
    }

    @PostMapping("/updateUserSecQuestion")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ReturnValue> updateUserSecQuestion(@RequestBody List<SecurityQuestion> secQuestions){
        ReturnValue returnValue = new ReturnValue();
        for (SecurityQuestion secQuestion : secQuestions){
            String secq_id = secQuestion.getSecq_id();
            String question = secQuestion.getQuestion();
            if(StringHelper.isEmpty(secq_id) && StringHelper.isEmpty(question)){
                continue;
            }
            if(StringHelper.isEmpty(question)){
                secQuestion.setQuestion(null);
                secQuestion.setAnswer(null);
                secQuestion.setState("0");
                userService.updateSecQuestion(secQuestion);
                continue;
            }
            secQuestion.setCreatetime(TimeCreator.nowStr());
            secQuestion.setState("1");
            if(StringHelper.isNotEmpty(secq_id)){
                userService.addSecQuestion(secQuestion);
                secQuestion.setQuestion(null);
                secQuestion.setAnswer(null);
                secQuestion.setState("0");
                userService.updateSecQuestion(secQuestion);
            }else{
                userService.addSecQuestion(secQuestion);
            }
        }
        returnValue.isSuccess();
        return ResponseEntity.ok(returnValue);
    }

    @GetMapping("/getUserSecQuestion")
    public ResponseEntity<ReturnValue<List<SecurityQuestion>>> getUserSecQuestion(@RequestParam String user_id){
        ReturnValue<List<SecurityQuestion>> returnValue = new ReturnValue<>();
        List<SecurityQuestion> secQuestions = userService.getUserSecQuestion(user_id);
        returnValue.setRetValue(secQuestions);
        returnValue.isSuccess();
        return ResponseEntity.ok(returnValue);
    }

    @GetMapping("/getUserSecQuestionNoAnswer")
    public ResponseEntity<ReturnValue<List<SecurityQuestion>>> getUserSecQuestionNoAnswer(@RequestParam String user_name){
        ReturnValue<List<SecurityQuestion>> returnValue = new ReturnValue<>();
        List<SecurityQuestion> secQuestions = userService.getUserSecQuestionByUsername(user_name);
        returnValue.setRetValue(secQuestions);
        returnValue.isSuccess();
        return ResponseEntity.ok(returnValue);
    }

    @GetMapping("/checkAnswer")
    public ResponseEntity<ReturnValue<Boolean>> checkAnswer(@RequestParam String secq_id,@RequestParam String answer){
        ReturnValue<Boolean> returnValue = new ReturnValue<>();
        boolean isRight = userService.chickAnswer(secq_id,answer);
        returnValue.isSuccess();
        returnValue.setRetValue(isRight);
        return ResponseEntity.ok(returnValue);
    }

    @PostMapping("/updateUserPassword")
    public ResponseEntity<ReturnValue<Boolean>> updateUserPassword(@RequestParam String user_name,@RequestParam String password){
        ReturnValue<Boolean> returnValue = new ReturnValue<>();
        userService.updateUserPassword(user_name,password);
        returnValue.isSuccess();
        returnValue.setRetValue(true);
        return ResponseEntity.ok(returnValue);
    }

    @PostMapping("/updateUserStatus")
    public ResponseEntity<ReturnValue> updateUserStatus(@RequestBody UserInfo userInfo){
        ReturnValue returnValue = new ReturnValue();
        String user_id = userInfo.getUser().getUser_id();
        UserInfo userInfo_t = userService.getUserInfoByUserId(user_id);
        userInfo.setLast_sign_in_date(TimeCreator.nowStr());
        if(StringHelper.isEmpty(userInfo_t.getLast_sign_in_date())){
            userService.addUserStatus(userInfo);
        }else{
            userService.updateUserStatus(userInfo);
        }
        returnValue.isSuccess();
        return ResponseEntity.ok(returnValue);
    }

}
