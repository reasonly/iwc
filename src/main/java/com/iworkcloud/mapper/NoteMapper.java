package com.iworkcloud.mapper;

import com.iworkcloud.pojo.Note;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
* Title: UserDao
* Description:
* 用户数据接口 
* Version:1.0.0
 */
@Mapper
public interface NoteMapper extends BaseMapper<Note> {

    List<Note> findByUserId(int userId);
    void insert(Note note);

}
