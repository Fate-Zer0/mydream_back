package com.example.mydream_back.services.model.pageBox;

import com.example.mydream_back.dao.model.PageBoxDAO;
import com.example.mydream_back.dao.model.TagDAO;
import com.example.mydream_back.dto.FileInfoDTO;
import com.example.mydream_back.dto.PageBox;
import com.example.mydream_back.dto.Tag;
import com.example.mydream_back.model.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageBoxServiceImpl implements PageBoxService{
    @Autowired
    private PageBoxDAO pageBoxDAO;
    @Autowired
    private TagDAO tagDAO;
    @Override
    public List<PageBox> queryPage() {
        List<PageBox> pageBoxes = pageBoxDAO.queryPage();
        for (PageBox pageBox: pageBoxes){
            List<Tag> tags = tagDAO.getTagListByObjidAndType(pageBox.getPb_id(), "page");
            pageBox.setTags(tags);
        }
        return pageBoxes;
    }
    @Override
    public void addPage(PageBox pageBox) {
        pageBox.setPb_state("1");
        pageBox.setStatus("最新");
        pageBoxDAO.addPage(pageBox);
        List<Tag> tags = pageBox.getTags();
        for (Tag tag: tags) {
            List<Tag> tagList = tagDAO.getTag(tag.getTag_name());
            if(tagList == null || tagList.size() == 0){
                tag.setTag_state("1");
                tagDAO.addTag(tag);
            }
            tagDAO.addTagConn(tag.getTag_id(),pageBox.getPb_id(),"page");
        }
        FileInfoDTO pb_img = pageBox.getPb_img();
        pageBox = pageBoxDAO.queryPageByImgid(pb_img.getFile_id());
        pageBoxDAO.updatePageImgFile(pageBox.getPb_id(),pb_img.getFile_id());
    }
    @Override
    public void InsertPageImgFile(FileInfoDTO fileInfoDTO){
        pageBoxDAO.InsertPageImgFile(fileInfoDTO);
    }
    @Override
    public FileInfoDTO queryPageImgFile(FileInfoDTO fileInfoDTO){
        return pageBoxDAO.queryPageImgFile(fileInfoDTO);
    }

    @Override
    public void updatePageImgFile(String user_id,int img_id){
        pageBoxDAO.updatePageImgFile(user_id,img_id);
    }
}
