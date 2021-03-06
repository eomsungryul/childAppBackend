package kr.co.dwebss.child.web;

import kr.co.dwebss.child.core.Result;
import kr.co.dwebss.child.core.ResultGenerator;
import kr.co.dwebss.child.model.EventCheck;
import kr.co.dwebss.child.service.EventCheckService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by 엄성렬 on 2018/07/22.
*/
@RestController
@RequestMapping("/child/evnet")
public class EventCheckController {
    @Resource
    private EventCheckService eventCheckService;
    

    @PostMapping
    public Result add(@RequestBody EventCheck eventCheck) {
        eventCheckService.save(eventCheck);
        return ResultGenerator.genSuccessResult();
    }
    

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        eventCheckService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping("/{eventCheckId}")
    public Result update(@RequestBody EventCheck eventCheck , @PathVariable Integer eventCheckId) {
    	eventCheck.setEventCheckId(eventCheckId);
        eventCheckService.update(eventCheck);
//        {event_check.checker_user_id(체크한사람의 user.user_id), event_check.event_check_cd(체크결과코드)}
        return ResultGenerator.genSuccessResult();
    }
    
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        EventCheck eventCheck = eventCheckService.findById(id);
        return ResultGenerator.genSuccessResult(eventCheck);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<EventCheck> list = eventCheckService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
