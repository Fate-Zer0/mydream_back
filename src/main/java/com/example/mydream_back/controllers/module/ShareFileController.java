package com.example.mydream_back.controllers.module;

import com.example.mydream_back.dao.model.TagDAO;
import com.example.mydream_back.dto.*;
import com.example.mydream_back.model.FileInfo;
import com.example.mydream_back.services.model.pageBox.PageBoxService;
import com.example.mydream_back.services.model.shareFile.ShareFileService;
import com.example.mydream_back.utils.FileHelper;
import com.example.mydream_back.utils.TimeCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/api/module/shareFile")
public class ShareFileController {
    @Autowired
    private PageBoxService pageBoxService;
    @Autowired
    private ShareFileService shareFileService;
    @Autowired
    private TagDAO tagDAO;
    @Autowired
    private ObjectMapper objectMapper;
    @PostMapping("/uploadShare")
    public ResponseEntity<ReturnValue<ShareFileInfo>> uploadImage(@RequestParam MultipartFile file, @RequestParam String shareFileInfoString) {
        ReturnValue<ShareFileInfo> returnValue = new ReturnValue<>();
        try {
            ShareFileInfo shareFileInfo = objectMapper.readValue(shareFileInfoString, ShareFileInfo.class);
            FileInfo fileInfo = FileHelper.uploadShareFile(file, "share/");

            // 设置文件信息
            FileInfoDTO fileInfoDTO = new FileInfoDTO();
            fileInfoDTO.setFile_path("/share/");
            fileInfoDTO.setFile_name(fileInfo.getStoredName());
            fileInfoDTO.setFile_type(10006);

            pageBoxService.InsertPageImgFile(fileInfoDTO);
            fileInfoDTO = pageBoxService.queryPageImgFile(fileInfoDTO);

            shareFileInfo.setShare_file(fileInfoDTO);
            shareFileInfo.setShare_time(TimeCreator.nowStr());

            shareFileService.addShareFile(shareFileInfo);
            List<Tag> tags = shareFileInfo.getTags();
            shareFileInfo = shareFileService.queryShareFileByFileid(fileInfoDTO.getFile_id());

            for (Tag tag: tags) {
                List<Tag> tagList = tagDAO.getTag(tag.getTag_name());
                if(tagList == null || tagList.size() == 0){
                    tag.setTag_state("1");
                    tagDAO.addTag(tag);
                }
                tagDAO.addTagConn(tag.getTag_id(),shareFileInfo.getShare_id(),"share");
            }

            pageBoxService.updatePageImgFile(shareFileInfo.getShare_id(),fileInfoDTO.getFile_id());

            returnValue.setRetValue(shareFileInfo);
            returnValue.isSuccess();
        }catch (Exception e) {
            e.printStackTrace();
            returnValue.isError();
            return ResponseEntity.ok(returnValue);
        }
        return ResponseEntity.ok(returnValue);
    }

    @GetMapping("/getShareList")
    public ResponseEntity<ReturnValue<List<ShareFileInfo>>> getShareList(String user_id){
        ReturnValue<List<ShareFileInfo>> ret = new ReturnValue<>();
        List<ShareFileInfo> shareList = shareFileService.queryShareFile();
        ret.setRetValue(shareList);
        ret.isSuccess();
        return ResponseEntity.ok(ret);
    }

}
