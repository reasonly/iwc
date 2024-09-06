package com.iworkcloud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectAttendance {

    private Integer joinId;
    private Integer projectId;
    private Integer userId;


}
