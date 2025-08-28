package com.example.mydream_back.controllers.module;

import com.example.mydream_back.dto.FileInfoDTO;
import com.example.mydream_back.dto.PageBox;
import com.example.mydream_back.dto.ReturnValue;
import com.example.mydream_back.dto.UserDTO;
import com.example.mydream_back.model.FileInfo;
import com.example.mydream_back.services.model.pageBox.PageBoxService;
import com.example.mydream_back.utils.FileHelper;
import com.example.mydream_back.utils.StringHelper;
import com.example.mydream_back.utils.TimeCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Time;
import java.util.List;

@Controller
@RequestMapping("/api/module/page")
public class PageBoxController {

    @Autowired
    private PageBoxService pageBoxService;

    @GetMapping("/getPageList")
    public ResponseEntity<ReturnValue<List<PageBox>>> getPageList(String user_id){
        ReturnValue<List<PageBox>> ret = new ReturnValue<>();
        List<PageBox> pageBoxList = pageBoxService.queryPage();
        ret.setRetValue(pageBoxList);
        ret.isSuccess();
        return ResponseEntity.ok(ret);
    }

    //上传图片
    @PostMapping("/uploadImage")
    public ResponseEntity<ReturnValue<FileInfoDTO>> uploadImage(@RequestParam MultipartFile file) {
        ReturnValue<FileInfoDTO> returnValue = new ReturnValue<>();
        try {
            FileInfo fileInfo = FileHelper.uploadFile(file, "pageimg/");

            // 设置文件信息
            FileInfoDTO fileInfoDTO = new FileInfoDTO();
            fileInfoDTO.setFile_path("/pageimg/");
            fileInfoDTO.setFile_name(fileInfo.getStoredName());
            fileInfoDTO.setFile_type(10005);

            pageBoxService.InsertPageImgFile(fileInfoDTO);
            fileInfoDTO = pageBoxService.queryPageImgFile(fileInfoDTO);

            returnValue.setRetValue(fileInfoDTO);
            returnValue.isSuccess();
        }catch (Exception e) {
            e.printStackTrace();
            returnValue.isError();
            return ResponseEntity.ok(returnValue);
        }
        return ResponseEntity.ok(returnValue);
    }

    @PostMapping("/addPage")
    public ResponseEntity<ReturnValue> addPage(@RequestBody PageBox pageBox){
        ReturnValue ret = new ReturnValue();
        pageBox.setCreatetime(TimeCreator.nowStr());
        pageBoxService.addPage(pageBox);
        ret.isSuccess();
        return ResponseEntity.ok(ret);
    }

}
