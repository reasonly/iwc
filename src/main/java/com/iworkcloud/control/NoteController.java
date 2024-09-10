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

        Map<String, Object> claims = new HashMap<>();
        List<Note> noteList=null;
        noteList = noteService.noteList(id);
        claims.put("noteList", noteList);
        return Results.Success(noteList);
    }

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
        if(noteDate.compareTo(remindDate) < 0){
            noteService.insert(note);
            return Results.Success();
        }else return Results.Error("提醒时间不能小于当前时间！");
    }

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
        Note note = new Note();
        System.out.println(request.get("noteId"));
        note.setNoteId(Integer.parseInt((String) request.get("noteId")));
        note.setNoteName((String) request.get("noteName"));
        note.setNoteBody((String) request.get("noteBody"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Timestamp noteDate = Timestamp.valueOf(formatter.format(date));
        note.setNoteDate(noteDate);
        Timestamp remindDate =Timestamp.valueOf((String) request.get("remindDate"));
        note.setRemindDate(remindDate);
        System.out.println(noteDate.compareTo(remindDate));
        if(noteDate.compareTo(remindDate) < 0){
            noteService.update(note);
            Map<String, Object> claims = new HashMap<>();
            claims.put("remindDate", note.getRemindDate());
            return Results.Success();
        }else return Results.Error("提醒时间不能小于当前时间！");
    }

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
        System.out.println(request.get("noteId"));
        List<Note> noteList=noteMapper.findByNoteId(Integer.parseInt((String) request.get("noteId")));
        System.out.println(noteMapper.findByNoteId(Integer.parseInt((String) request.get("noteId"))));
        if(noteList.size() == 0){
            return Results.Error("该记事本不存在！");
        }else{
            noteMapper.deleteNote(Integer.parseInt((String) request.get("noteId")));
            return Results.Success();
        }

    }

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
        Note note = new Note();
        note.setNoteName((String) request.get("noteName"));
        note.setNoteBody((String) request.get("noteBody"));
        note.setRemindDate((Timestamp) request.get("remindDate"));
        note.setUserId(id);
        List<Note> noteList = noteService.findByListEntity(note);
        if(noteList.size() == 0){
            return Results.Error("没有找到相关记事本！");
        }else {
            Map<String, Object> claims = new HashMap<>();
            claims.put("noteList", noteList);
            return Results.Success(noteList);
        }

    }

}
