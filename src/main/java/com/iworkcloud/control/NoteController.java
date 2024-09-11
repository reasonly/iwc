package com.iworkcloud.control;

import com.iworkcloud.mapper.NoteMapper;
import com.iworkcloud.pojo.Note;
import com.iworkcloud.pojo.Project;
import com.iworkcloud.pojo.Results;
import com.iworkcloud.service.NoteService;
import com.iworkcloud.service.ProjectService;
import com.iworkcloud.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/note")
public class NoteController {
    @Autowired
    private NoteService noteService;
    @Autowired
    private NoteMapper noteMapper;

    // 获取所有记事本
    @RequestMapping("/notelist")
    public Results noteList(HttpServletRequest Request){
        int id = 0;
        try{
            String jwt = Request.getHeader("token");
            System.out.println("解析jwt="+jwt);
            Map<String, Object> claim = JwtUtils.ParseJwt(jwt);
            id = (int) claim.get("id");
            String authority =(String) claim.get("authority");
            System.out.println("解析令牌得id="+id+" authority="+authority);
        }catch (Exception e){
            return Results.Error("token过期，请重新登录！");
        }
        // 返回记事本列表
        Map<String, Object> claims = new HashMap<>();
        List<Note> noteList=null;
        noteList = noteService.noteList(id);
        claims.put("noteList", noteList);
        return Results.Success(noteList);
    }

    // 添加记事本
    @RequestMapping("/addnote")
    public Results addNote(HttpServletRequest Request,@RequestBody Map<String, Object> request){
        int id = 0;
        try{
            String jwt = Request.getHeader("token");
            System.out.println("解析jwt="+jwt);
            Map<String, Object> claim =JwtUtils.ParseJwt(jwt);
            id = (int) claim.get("id");
            String authority =(String) claim.get("authority");
            System.out.println("解析令牌得id="+id+" authority="+authority);
        }catch (Exception e){
            return Results.Error("token过期，请重新登录！");
        }
        // 获得记事本信息
        Note note = new Note();
        note.setNoteName((String) request.get("noteName"));
        note.setNoteBody((String) request.get("noteBody"));
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(date));
        Timestamp noteDate = Timestamp.valueOf(formatter.format(date));
        note.setNoteDate(noteDate);
        System.out.println(request.get("remindDate"));
        Timestamp remindDate =Timestamp.valueOf((String) request.get("remindDate"));
        note.setRemindDate(remindDate);
        note.setUserId(id);
        // 判断提醒时间是否小于当前时间
        if(noteDate.compareTo(remindDate) < 0){
            noteService.insert(note);
            return Results.Success();
        }else return Results.Error("提醒时间不能小于当前时间！");
    }

    // 编辑记事本
    @RequestMapping("/edit")
    public Results editNote(HttpServletRequest Request,@RequestBody Map<String, Object> request){
        int id = 0;
        try{
            String jwt = Request.getHeader("token");
            System.out.println("解析jwt="+jwt);
            Map<String, Object> claim =JwtUtils.ParseJwt(jwt);
            id = (int) claim.get("id");
            String authority =(String) claim.get("authority");
            System.out.println("解析令牌得id="+id+" authority="+authority);
        }catch (Exception e){
            return Results.Error("token过期，请重新登录！");
        }
        // 获得修改信息
        Note note = new Note();
        System.out.println(request.get("noteId"));
        note.setNoteId((Integer) request.get("noteId"));
        note.setNoteName((String) request.get("noteName"));
        note.setNoteBody((String) request.get("noteBody"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Timestamp noteDate = Timestamp.valueOf(formatter.format(date));
        note.setNoteDate(noteDate);
        Timestamp remindDate =Timestamp.valueOf((String) request.get("remindDate"));
        note.setRemindDate(remindDate);
        System.out.println(noteDate.compareTo(remindDate));
        // 判断提醒时间是否小于当前时间
        if(noteDate.compareTo(remindDate) < 0){
            noteService.update(note);
            Map<String, Object> claims = new HashMap<>();
            claims.put("remindDate", note.getRemindDate());
            return Results.Success();
        }else return Results.Error("提醒时间不能小于当前时间！");
    }

    // 删除记事本
    @RequestMapping("/delete")
    public Results deleteNote(HttpServletRequest Request,@RequestBody Map<String, Object> request){
        int id = 0;
        try{
            String jwt = Request.getHeader("token");
            System.out.println("解析jwt="+jwt);
            Map<String, Object> claim =JwtUtils.ParseJwt(jwt);
            id = (int) claim.get("id");
            String authority =(String) claim.get("authority");
            System.out.println("解析令牌得id="+id+" authority="+authority);
        }catch (Exception e){
            return Results.Error("token过期，请重新登录！");
        }
        // 获得删除会议的id
        System.out.println(request.get("noteId"));
        List<Note> noteList=noteMapper.findByNoteId((Integer) request.get("noteId"));
        System.out.println(noteMapper.findByNoteId((Integer) request.get("noteId")));
        if(noteList.size() == 0){
            return Results.Error("该记事本不存在！");
        }else{
            noteMapper.deleteNote((Integer) request.get("noteId"));
            return Results.Success();
        }

    }

    // 搜索记事本
    @RequestMapping("/search")
    public Results search(HttpServletRequest Request,@RequestBody Map<String, Object> request){
        int id = 0;
        try{
            String jwt = Request.getHeader("token");
            System.out.println("解析jwt="+jwt);
            Map<String, Object> claim =JwtUtils.ParseJwt(jwt);
            id = (int) claim.get("id");
            String authority =(String) claim.get("authority");
            System.out.println("解析令牌得id="+id+" authority="+authority);
        }catch (Exception e){
            return Results.Error("token过期，请重新登录！");
        }
        // 获得搜索信息
        Note note = new Note();
        note.setNoteName((String) request.get("noteName"));
        note.setNoteBody((String) request.get("noteBody"));
        note.setUserId(id);
        List<Note> noteList = noteService.findByListEntity(note);
        // 判断搜索结果
        if(noteList.size() == 0){
            return Results.Error("没有找到相关记事本！");
        }else {
            Map<String, Object> claims = new HashMap<>();
            claims.put("noteList", noteList);
            return Results.Success(noteList);
        }

    }

}
