package com.iworkcloud.control;

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
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/note")
public class NoteController {
    @Autowired
    private NoteService noteService;

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

        List<Note> noteList=null;
        noteList = noteService.noteList(id);
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
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        System.out.println(formatter.format(date));
        note.setNoteDate(Timestamp.valueOf(formatter.format(date)));
        note.setRemindDate((Timestamp) request.get("remindDate"));
        noteService.insert(note);
        return Results.Success();
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
        note.setNoteName((String) request.get("noteName"));
        note.setNoteBody((String) request.get("noteBody"));
        note.setRemindDate((Timestamp) request.get("remindDate"));
        noteService.update(note);
        return Results.Success();
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
        noteService.deleteByPrimaryKey(id);
        return Results.Success();
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
        List<Note> noteList = noteService.findByListEntity(note);
        return Results.Success(noteList);
    }

    @RequestMapping("/update")
    public Results adminSearch(HttpServletRequest Request,@RequestBody Map<String, Object> request){
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
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        note.setNoteDate(Timestamp.valueOf(formatter.format(date)));
        note.setRemindDate((Timestamp) request.get("remindDate"));
        noteService.update(note);
        return Results.Success(note);
    }


}
