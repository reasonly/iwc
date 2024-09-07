package com.iworkcloud.service;

import com.iworkcloud.pojo.Note;

import java.util.List;


/**
 * 
* Title: UserService
* Description: 
* 用户接口 
* Version:1.0.0  

 */
public interface NoteService extends BaseService<Note>{

    List<Note> noteList(Integer id);
}
