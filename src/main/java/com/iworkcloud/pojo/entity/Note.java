package com.iworkcloud.pojo.entity;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.sql.Timestamp;

public class Note {
    private Integer noteId;
    private String noteName;
    private String noteBody;
    private Timestamp noteDate;
    private Timestamp remindDate;
    private Integer administratorId;
    private Integer userId;

    @Override
    public String toString() {
        return "Note{" +
                "noteId=" + noteId +
                ", noteName='" + noteName + '\'' +
                ", noteBody='" + noteBody + '\'' +
                ", noteDate=" + noteDate +
                ", remindDate=" + remindDate +
                ", administratorId=" + administratorId +
                ", userId=" + userId +
                '}';
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public String getNoteBody() {
        return noteBody;
    }

    public void setNoteBody(String noteBody) {
        this.noteBody = noteBody;
    }



    public Integer getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(Integer administratorId) {
        this.administratorId = administratorId;
    }

    public Timestamp getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(Timestamp noteDate) {
        this.noteDate = noteDate;
    }

    public Timestamp getRemindDate() {
        return remindDate;
    }

    public void setRemindDate(Timestamp remindDate) {
        this.remindDate = remindDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
