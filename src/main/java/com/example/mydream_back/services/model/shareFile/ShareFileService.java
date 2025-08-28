package com.example.mydream_back.services.model.shareFile;

import com.example.mydream_back.dto.ShareFileInfo;

import java.util.List;

public interface ShareFileService {

    void addShareFile(ShareFileInfo shareFileInfo);
    ShareFileInfo queryShareFileByFileid(int file_id);
    List<ShareFileInfo> queryShareFile();
}
