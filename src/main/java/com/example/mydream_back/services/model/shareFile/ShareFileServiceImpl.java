package com.example.mydream_back.services.model.shareFile;

import com.example.mydream_back.dao.model.ShareFileDAO;
import com.example.mydream_back.dto.ShareFileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShareFileServiceImpl implements ShareFileService{

    @Autowired
    private ShareFileDAO shareFileDAO;

    @Override
    public void addShareFile(ShareFileInfo shareFileInfo) {
        shareFileDAO.addShareFile(shareFileInfo);
    }
    @Override
    public ShareFileInfo queryShareFileByFileid(int file_id) {
        return shareFileDAO.queryShareFileByFileid(file_id);
    }
    @Override
    public List<ShareFileInfo> queryShareFile() {
        return shareFileDAO.queryShareFile();
    }

}
