package com.iworkcloud.pojo;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {
    private Integer noteId;
    private String noteName;
    private String noteBody;
    private Timestamp noteDate;
    private Timestamp remindDate;
    private Integer userId;


}
