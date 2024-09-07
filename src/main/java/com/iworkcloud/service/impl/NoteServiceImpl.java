package com.iworkcloud.service.impl;

import com.iworkcloud.mapper.BaseMapper;
import com.iworkcloud.mapper.NoteMapper;
import com.iworkcloud.pojo.Note;
import com.iworkcloud.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl extends BaseServiceImpl<Note> implements NoteService {
    //mybatis
    @Autowired
    private NoteMapper noteMapper;

    @Override
    protected BaseMapper<Note> getMapper() {
        return this.noteMapper;
    }

    @Override
    public List<Note> noteList(Integer id){
        return noteMapper.findByUserId(id);
    }




}
