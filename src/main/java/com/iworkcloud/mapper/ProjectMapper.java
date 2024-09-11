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
    //根据项目实体查询项目列表
    List<Project> findByListEntity(@Param("Project")Project project);
    //为指定项目添加用户
    boolean addUser(Integer projectId, List<Integer> userIdList);
    //查询不在指定项目中的用户列表
    List<User> findUsersNotInProject(Integer projectId, String authority);
    //查询在指定项目中的用户列表
    List<User> findUsersInProject(Integer projectId, String authority);
}
