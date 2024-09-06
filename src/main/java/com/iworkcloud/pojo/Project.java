package com.iworkcloud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private Integer projectId;
    private String projectName;
    private String projectContent;
    private String projectState;
    private Integer administratorId;


}
