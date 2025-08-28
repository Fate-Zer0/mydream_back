package com.example.mydream_back.dao.model;

import com.example.mydream_back.dto.PageBox;
import com.example.mydream_back.dto.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagDAO {

    List<Tag> getTagListByObjidAndType(@Param("obj_id")String obj_id,@Param("obj_type") String obj_type);
    void addTagConn(@Param("tag_id") String tag_id,@Param("obj_id") String obj_id,@Param("obj_type") String obj_type);
    void addTag(Tag tag);
    void deleteTag(Tag tag);
    List<Tag> getTag(@Param("tag_name")String tag_name);
}
