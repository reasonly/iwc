package com.iworkcloud.control;

import com.iworkcloud.pojo.Leave;
import com.iworkcloud.pojo.Results;
import com.iworkcloud.service.LeaveService;
import com.iworkcloud.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/LeaveController")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;
    @RequestMapping("/leaveList")
    public Results askLeaveList(HttpServletRequest Request) {
        System.out.println("访问localhost:9000/LeaveController/leaveList !");
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
        List<Leave> leaveList=leaveService.findAllByUserId(id);
        System.out.println("List<leave>" + leaveList);
        claims.put("leaveList", leaveList);
        return Results.Success(claims);
    }
    @RequestMapping("/askLeave")
    public Results askLeave(HttpServletRequest Request,@RequestBody Map<String, Object> request) {

        System.out.println("访问localhost:9000/LeaveController/askLeave !");
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

        Leave leave = new Leave();

        String startDate=request.get("startDate").toString();
        String endDate = request.get("endDate").toString();
        leave.setStartDate(Date.valueOf(startDate));
        leave.setEndDate(Date.valueOf(endDate));

        leave.setReason((String) request.get("reason"));
        leave.setState("未审批");
        leave.setUserId(id);
        System.out.println("请假信息表生成如："+leave);

        Leave findLeave = leaveService.findLeaveByStartDateAndEndDate(leave);
        if(findLeave!=null)return Results.Error("请不要重复请假！");
        else{
            boolean isInsert = leaveService.insert(leave);
            if(isInsert)
                return Results.Success("请假申请成功！");
            else return Results.Error("请假申请失败！");
        }


    }
}
