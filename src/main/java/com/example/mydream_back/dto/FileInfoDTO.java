package com.example.mydream_back.dto;

public class FileInfoDTO {
    private int file_id;
    private String file_path;
    private String file_name;
    private String file_url;
    private int file_type;

    public void setFile_url() {
        this.file_url = "/file"+file_path+file_name;
    }

    public String getFile_url() {
        return "/file"+file_path+file_name;
    }

    public int getFile_id() {
        return file_id;
    }

    public void setFile_id(int file_id) {
        this.file_id = file_id;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
        setFile_url();
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
        setFile_url();
    }

    public int getFile_type() {
        return file_type;
    }

    public void setFile_type(int file_type) {
        this.file_type = file_type;
    }
}
