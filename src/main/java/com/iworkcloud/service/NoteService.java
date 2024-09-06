package com.iworkcloud.service;

import com.iworkcloud.pojo.Note;


/**
 * 
* Title: UserService
* Description: 
* 用户接口 
* Version:1.0.0  

 */
public interface NoteService extends BaseService<Note>{

    Boolean findByUserId(Note note);
    Boolean findByAdministratorId(Note note);

}
