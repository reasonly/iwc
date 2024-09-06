package com.iworkcloud.service.impl;

import com.iworkcloud.mapper.BaseMapper;
import com.iworkcloud.mapper.NoteMapper;
import com.iworkcloud.pojo.Note;
import com.iworkcloud.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl extends BaseServiceImpl<Note> implements NoteService {
    //mybatis
    @Autowired
    private NoteMapper noteMapper;

    @Override
    protected BaseMapper<Note> getMapper() {
        return this.noteMapper;
    }

    /**
     * @param note 用户实体
     * @return 查询结果
     */
    @Override
    public Boolean findByUserId(Note note) {
        try {
            System.out.println("findByUserId");
            Note note1 = noteMapper.findByUserId(note);
            return note1 != null;
        } catch (Exception e) {
            System.out.println("查询失败!");
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public Boolean findByAdministratorId(Note note) {
        try {
            System.out.println("findByAdministratorId");
            Note note1 = noteMapper.findByAdministratorId(note);
            return note1 != null;
        } catch (Exception e) {
            System.out.println("查询失败!");
            e.printStackTrace();
        }
        return false;
    }
}
