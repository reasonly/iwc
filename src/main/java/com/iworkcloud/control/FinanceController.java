package com.iworkcloud.control;

import com.iworkcloud.pojo.Finance;
import com.iworkcloud.pojo.FinanceManage;
import com.iworkcloud.pojo.Results;
import com.iworkcloud.service.FinanceService;
import com.iworkcloud.service.ProjectService;
import com.iworkcloud.util.JwtUtils;
import jdk.nashorn.internal.runtime.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.soap.MimeHeaders;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/finance")
public class FinanceController {
    private static final Logger log = LoggerFactory.getLogger(FinanceController.class);
    @Autowired
    private FinanceService financeService;

    @GetMapping("/list")
    public Results financeList(HttpServletRequest Request){
        int id = 0;

        try{
            String jwt = Request.getHeader("token");
            System.out.println("解析jwt="+jwt);
            Map<String, Object> claim =JwtUtils.ParseJwt(jwt);
            id = (int) claim.get("id");
            System.out.println("id :"+id);

        }catch (Exception e){

            return Results.Error("token过期，请重新登录！");
        }
        List<Finance> financeList = financeService.financeList();
        if(financeList.size()==0){
            return Results.Error("没有财务记录");
        }
        return Results.Success(financeList);
    }
    @GetMapping("/search")
    public Results adminSearch(HttpServletRequest Request,@RequestBody Map<String, Object> request){
        int id = 0;

        try{
            String jwt = Request.getHeader("token");
            System.out.println("解析jwt="+jwt);
            Map<String, Object> claim =JwtUtils.ParseJwt(jwt);
            id = (int) claim.get("id");
            System.out.println("id :"+id);

        }catch (Exception e){

            return Results.Error("token过期，请重新登录！");
        }
        try {
            Finance finance= new Finance();
            finance.setFinanceType((String) request.get("financeType"));
            finance.setAmount((Double) request.get("amount"));
            finance.setFinanceDescription((String) request.get("financeDescription"));
            finance.setProjectId((Integer) request.get("projectId"));

            List<Finance> financeList = financeService.financeList(finance);
            if(financeList.size()==0){
                return Results.Error("没有财务记录");
            }
            return Results.Success(financeList);
        }
        catch (Exception e){
            log.error("搜索失败",e);
            return Results.Error("搜索失败");
        }

    }

    /**
     * 添加财务信息
     * 会产生一个操作财务记录，同时将操作记录在t_finance_mamange表中
     *
     * @param request
     * @return
     */
    @PostMapping("/add")
    public Results add(HttpServletRequest Request, @RequestBody Map<String, Object> request){
        int id = 0;
        try{
            String jwt = Request.getHeader("token");
            System.out.println("解析jwt="+jwt);
            Map<String, Object> claim =JwtUtils.ParseJwt(jwt);
            id = (int) claim.get("id");
            System.out.println("id :"+id);

        }catch (Exception e){

            return Results.Error("token过期，请重新登录！");
        }

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String datetime =LocalDateTime.now().format(formatter);
//       Timestamp time = Timestamp.valueOf();
        try {
            Finance finance = getFinance(request);
            if(financeService.addFinance(finance)){
                //判断是否是项目的财务信息
                if(finance.getProjectId()!=null){
                    financeService.updateProjectTotal(finance.getProjectId());
                }
                Integer financeId = financeService.findFianceIdByFinance(finance);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String datetime =LocalDateTime.now().format(formatter);
                Timestamp financeManageTime = Timestamp.valueOf(datetime);

                FinanceManage financeManage = new FinanceManage(null, financeId, id, financeManageTime, "添加财务信息");
                if(financeService.addFinanceManage(financeManage)) {
                    return Results.Success("添加成功");
                }
                return Results.Error("添加财务操作记录失败");
            }

        }
        catch (Exception e){
    
            log.error("添加失败",e);
        }
        return Results.Error("添加财务记录失败");
    }

    /**
     * 删除财务信息
     * @param request
     */
    @DeleteMapping("/delete")
    public Results delete(HttpServletRequest Request,@RequestBody Map<String, Object> request){
        int id = 0;

        try{
            String jwt = Request.getHeader("token");
            System.out.println("解析jwt="+jwt);
            Map<String, Object> claim =JwtUtils.ParseJwt(jwt);
            id = (int) claim.get("id");
            System.out.println("id :"+id);

        }catch (Exception e){

            return Results.Error("token过期，请重新登录！");
        }

        try{
            Integer financeId = (Integer) request.get("financeId");
            if(financeService.deleteByPrimaryKey(financeId)) {
                return Results.Success("删除成功");
            }
        }
        catch (Exception e){
            log.error("删除失败",e);
        }
        return Results.Error("删除失败");
    }

    /**
     * 修改财务信息
     * @param request
     * @param Request
     * @return
     */
    @PutMapping("/update")
    public Results update(@RequestBody Map<String, Object> request,HttpServletRequest Request){
        int id = 0;

        try{
            String jwt = Request.getHeader("token");
            System.out.println("解析jwt="+jwt);
            Map<String, Object> claim =JwtUtils.ParseJwt(jwt);
            id = (int) claim.get("id");
            System.out.println("id :"+id);

        }catch (Exception e){

            return Results.Error("token过期，请重新登录！");
        }


        try{
            Finance finance = getFinance(request);
            if(financeService.updateFinance(finance)){
                //判断是否是项目的财务信息
                if(finance.getProjectId()!=null){
                    financeService.updateProjectTotal(finance.getProjectId());
                }

                Integer financeId = finance.getProjectId();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String datetime =LocalDateTime.now().format(formatter);
                Timestamp financeManageTime = Timestamp.valueOf(datetime);

                FinanceManage financeManage = new FinanceManage(null, financeId, id, financeManageTime, "修改财务信息");
                if(financeService.addFinanceManage(financeManage)) {
                    return Results.Success("修改成功");
                }
                return Results.Error("添加财务操作记录失败");
            }

        }  catch (Exception e){
            log.error("修改失败",e);
            }
        return Results.Error("修改失败");
    }
    private Finance getFinance(Map<String, Object> request){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String datetime =LocalDateTime.now().format(formatter);
        Timestamp financeRecordTime = Timestamp.valueOf(datetime);
        return new Finance((Integer) request.get("financeId"), (String) request.get("financeType"), (Double) request.get("amount"), (String) request.get("financeDescription"), financeRecordTime, (Integer) request.get("userId"), (Integer) request.get("projectId"));
    }
    private Integer getUserId(HttpServletRequest Request){

        String jwt = Request.getHeader("token");
        Map<String, Object> claim = JwtUtils.ParseJwt(jwt);
        return (Integer) claim.get("id");
    }
}
