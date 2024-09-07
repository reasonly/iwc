package com.iworkcloud.control;

import com.iworkcloud.pojo.Finance;
import com.iworkcloud.pojo.FinanceManage;
import com.iworkcloud.pojo.Results;
import com.iworkcloud.service.FinanceService;
import com.iworkcloud.service.ProjectService;
import com.iworkcloud.util.JwtUtils;
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
    @Autowired
    private FinanceService financeService;

    @GetMapping("/list")
    public Results financeList(){
        List<Finance> financeList = financeService.financeList();
        return Results.Success(financeList);
    }
    @GetMapping("/search")
    public Results adminSearch(Map<String, Object> request){
        Finance finance= getFinance(request);
        List<Finance> financeList = financeService.financeList(finance);
        return Results.Success(financeList);
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
        Finance finance = getFinance(request);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String datetime =LocalDateTime.now().format(formatter);
//       Timestamp time = Timestamp.valueOf();

        financeService.addFinance(finance);
        //判断是否是项目的财务信息
        if(finance.getProjectId()!=null){
            financeService.updateProjectTotal(finance.getProjectId());
        }
        Integer financeId = financeService.findFianceIdByFinance(finance);
        String jwt = Request.getHeader("token");
        Map<String, Object> claim = JwtUtils.ParseJwt(jwt);
        int id = (int) claim.get("id");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String datetime =LocalDateTime.now().format(formatter);
        Timestamp financeManageTime = Timestamp.valueOf(datetime);
        FinanceManage financeManage = new FinanceManage(null, financeId, id, financeManageTime, "添加财务信息");
        financeService.addFinanceManage(financeManage);
        return Results.Success("添加成功");
    }

    /**
     * 删除财务信息
     * @param request
     */
    @DeleteMapping("/delete")
    public Results delete(@RequestBody Map<String, Object> request){
        Integer financeId = (Integer) request.get("financeId");
        financeService.deleteByPrimaryKey(financeId);
        return Results.Success("删除成功");
    }
    private Finance getFinance(Map<String, Object> request){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String datetime =LocalDateTime.now().format(formatter);
        Timestamp financeRecordTime = Timestamp.valueOf(datetime);
        return new Finance((Integer) request.get("financeId"), (String) request.get("financeType"), (Double) request.get("amount"), (String) request.get("financeDescription"), financeRecordTime, (Integer) request.get("userId"), (Integer) request.get("projectId"));
    }
}
