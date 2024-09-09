package com.iworkcloud.control;

import com.iworkcloud.pojo.Attendance;
import com.iworkcloud.pojo.Leave;
import com.iworkcloud.pojo.LeaveApproval;
import com.iworkcloud.pojo.Results;
import com.iworkcloud.service.AttendanceService;
import com.iworkcloud.service.LeaveApprovalService;
import com.iworkcloud.service.LeaveService;
import com.iworkcloud.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("LeaveApprovalController")
public class LeaveApprovalController {

    @Autowired
    private LeaveService leaveService;
    @Autowired
    private LeaveApprovalService leaveApprovalService;
    @Autowired
    private AttendanceService attendanceService;


    @RequestMapping("/leaveList")
    public Results leaveApprovalList(HttpServletRequest Request) {
        System.out.println("访问localhost:9000/LeaveApprovalController/leaveList !");
        int id = 0;
        try{
            String jwt = Request.getHeader("token");
            System.out.println("解析jwt="+jwt);
            Map<String, Object> claim = JwtUtils.ParseJwt(jwt);
            id = (int) claim.get("id");
            System.out.println("id="+id);
        }catch (Exception e){
            return Results.Error("token过期，请重新登录！");
        }

        Map<String, Object> claims = new HashMap<>();
        List<Leave> leaveList=leaveService.findAll();
        System.out.println("List<leave>" + leaveList);
        claims.put("leaveApprovalList", leaveList);
        return Results.Success(claims);

    }

    @RequestMapping("/leaveUnapprovedList")
    public Results askLeaveList(HttpServletRequest Request) {
        System.out.println("访问localhost:9000/LeaveApprovalController/leaveUnapprovedList !");
        int id = 0;
        try{
            String jwt = Request.getHeader("token");
            System.out.println("解析jwt="+jwt);
            Map<String, Object> claim = JwtUtils.ParseJwt(jwt);
            id = (int) claim.get("id");
            System.out.println("id="+id);
        }catch (Exception e){
            return Results.Error("token过期，请重新登录！");
        }
        Map<String, Object> claims = new HashMap<>();
        List<Leave> leaveList=leaveService.findleaveUnapproved("未审批");
        System.out.println("leaveList:" + leaveList);
        claims.put("leaveList查找成功：", leaveList);
        return Results.Success(claims);
    }


    @RequestMapping("/leaveApproval")
    public Results leaveApproval(HttpServletRequest Request,@RequestBody Map<String, Object> request) {
        System.out.println("访问localhost:9000/LeaveApprovalController/leaveApproval !");
        int id = 0;
        try{
            String jwt = Request.getHeader("token");
            System.out.println("解析jwt="+jwt);
            Map<String, Object> claim =JwtUtils.ParseJwt(jwt);
            id = (int) claim.get("id");
            System.out.println("id="+id);
        }catch (Exception e){
            return Results.Error("token过期，请重新登录！");
        }

        Integer leaveId = (Integer)request.get("leaveId");
        String state = request.get("state").toString();
        System.out.println("获得leaveId="+leaveId+"  state="+ state);
        LeaveApproval la = new LeaveApproval();
        Leave lea;

        try {
            //LeaveApproval
            la.setLeaveId(leaveId);
            la.setUserId(id);//审批人id
            //Leava
            lea=leaveService.findLeaveByLeaveId(leaveId);
            System.out.println("生成请假审批表："+la);
            System.out.println("找到请假表："+lea);
        }catch (Exception e){
            System.out.println(e);
            return Results.Error("请假审批表生成失败，或未找到该请假表！");
        }

        if(lea.getState().equals("未审批")){
            if(state.equals("未批准")){
                //修改请假表审批状态
                lea.setState(state);
                System.out.println("修改其审批状态："+lea);
                leaveService.update(lea);
                //添加审批日志
                leaveApprovalService.insert(la);
                return Results.Success("未批准,成功审批！");
            }else if(state.equals("已批准")){
                //修改请假表审批状态
                lea.setState(state);
                System.out.println("修改其审批状态："+lea);
                leaveService.update(lea);
                //如果请今天假期，修改当日考勤状态

                Attendance a=new Attendance();
                a.setDate(lea.getStartDate());
                a.setUserId(lea.getUserId());
                System.out.println("a："+a);

                Attendance atd=attendanceService.findAttendanceByDateAndUserId(a);
                if(atd != null){
                    atd.setAttendanceState("请假");
                    System.out.println("考勤修改预览："+atd);
                    attendanceService.attendanceByAttendanceId(atd);
                }
                //添加审批日志
                leaveApprovalService.insert(la);
                return Results.Success("已批准,成功审批！");
            }else return Results.Error("审批意见出错！");
        }else return Results.Error("该请假申请已被审批！");


    }
}
