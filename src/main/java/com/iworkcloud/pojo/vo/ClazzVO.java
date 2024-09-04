package com.iworkcloud.pojo.vo;

import java.util.List;

public class ClazzVO extends Clazz {

    private List<Student> studentList;

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "ClazzVO{" +
                "id=" + getId() +
                ", className='" + getClassName() + '\'' +
                ", studentList=" + studentList +
                '}';
    }
}
