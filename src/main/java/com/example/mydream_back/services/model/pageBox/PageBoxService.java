package com.example.mydream_back.services.model.pageBox;

import com.example.mydream_back.dto.FileInfoDTO;
import com.example.mydream_back.dto.PageBox;

import java.util.List;

public interface PageBoxService {
    List<PageBox> queryPage();
    void addPage(PageBox pageBox);
    void InsertPageImgFile(FileInfoDTO fileInfoDTO);
    FileInfoDTO queryPageImgFile(FileInfoDTO fileInfoDTO);
    void updatePageImgFile(String user_id,int img_id);
}
