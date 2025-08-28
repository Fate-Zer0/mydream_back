package com.example.mydream_back.services.model.tag;

import com.example.mydream_back.dao.model.TagDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagSericeImpl implements TagService{

    @Autowired
    private TagDAO tagDAO;

}
