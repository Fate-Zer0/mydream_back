package com.example.mydream_back.dao.model;

import com.example.mydream_back.dto.FileInfoDTO;
import com.example.mydream_back.dto.PageBox;
import com.example.mydream_back.dto.ShareFileInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShareFileDAO {
    void addShareFile(ShareFileInfo shareFileInfo);
    List<ShareFileInfo> queryShareFile();
    ShareFileInfo queryShareFileByFileid(int file_id);
}
