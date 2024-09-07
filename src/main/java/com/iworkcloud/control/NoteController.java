package com.iworkcloud.control;

import com.iworkcloud.pojo.Note;
import com.iworkcloud.pojo.Project;
import com.iworkcloud.pojo.Results;
import com.iworkcloud.service.NoteService;
import com.iworkcloud.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Results noteList(Model module, HttpSession session){
        List<Note> noteList=null;
        noteList = noteService.noteList((Integer) session.getAttribute("userId"));
        return Results.Success(noteList);
    }

    @RequestMapping("/addnote")
    public void addNote(@RequestBody Map<String, Object> request){
        Note note = new Note();
        note.setNoteName((String) request.get("noteName"));
        note.setNoteBody((String) request.get("noteBody"));
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        System.out.println(formatter.format(date));
        note.setNoteDate(Timestamp.valueOf(formatter.format(date)));
        note.setRemindDate((Timestamp) request.get("remindDate"));
        noteService.insert(note);
    }

    @RequestMapping("/edit")
    public void editNote(@RequestBody Map<String, Object> request){
        Note note = new Note();
        note.setNoteName((String) request.get("noteName"));
        note.setNoteBody((String) request.get("noteBody"));
        note.setRemindDate((Timestamp) request.get("remindDate"));
        noteService.update(note);
    }

    @RequestMapping("/delete")
    public void deleteNote(@RequestBody Map<String, Object> request){
        Integer id = (Integer) request.get("id");
        noteService.deleteByPrimaryKey(id);
    }

    @RequestMapping("/search")
    public Results search(@RequestBody Map<String, Object> request){
        Note note = new Note();
        note.setNoteName((String) request.get("noteName"));
        note.setNoteBody((String) request.get("noteBody"));
        note.setRemindDate((Timestamp) request.get("remindDate"));
        List<Note> noteList = noteService.findByListEntity(note);
        return Results.Success(noteList);
    }

    @RequestMapping("/update")
    public Results adminSearch(@RequestBody Map<String, Object> request){
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
