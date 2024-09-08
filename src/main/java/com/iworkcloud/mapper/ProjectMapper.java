package com.iworkcloud.mapper;

import com.iworkcloud.pojo.Project;
import com.iworkcloud.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectMapper extends BaseMapper<Project>{
//    Project findByProjectWords(String projectWords);
    //根据用户id查询
    List<Project> findByUserId(int id);
    //根据条件和用户id查询
    List<Project> findByListEntityAndUserId(@Param("Project")Project project,@Param("id") Integer id);
    List<Project> findByListEntity(@Param("Project")Project project);

    boolean addUser(Integer projectId, List<Integer> userIdList);

    List<User> findUsersNotInProject(Integer projectId, String authority);

    List<User> findUsersInProject(Integer projectId, String authority);
}
