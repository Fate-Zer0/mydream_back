package com.example.mydream_back.dao.model;

import com.example.mydream_back.dto.FileInfoDTO;
import com.example.mydream_back.dto.PageBox;
import com.example.mydream_back.model.FileInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PageBoxDAO {
    void addPage(PageBox pageBox);
    List<PageBox> queryPage();
    PageBox queryPageByImgid(int img_id);
    void InsertPageImgFile(FileInfoDTO fileInfoDTO);
    void updatePageImgFile(String user_id,int img_id);
    FileInfoDTO queryPageImgFile(FileInfoDTO fileInfoDTO);
}
