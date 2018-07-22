package kr.co.dwebss.child.web;

import kr.co.dwebss.child.core.Result;
import kr.co.dwebss.child.core.ResultGenerator;
import kr.co.dwebss.child.model.ClassDailyEvent;
import kr.co.dwebss.child.service.ClassDailyEventService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by 엄성렬 on 2018/07/22.
*/
@RestController
@RequestMapping("/class/daily/event")
public class ClassDailyEventController {
    @Resource
    private ClassDailyEventService classDailyEventService;

    @PostMapping
    public Result add(@RequestBody ClassDailyEvent classDailyEvent) {
        classDailyEventService.save(classDailyEvent);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        classDailyEventService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody ClassDailyEvent classDailyEvent) {
        classDailyEventService.update(classDailyEvent);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        ClassDailyEvent classDailyEvent = classDailyEventService.findById(id);
        return ResultGenerator.genSuccessResult(classDailyEvent);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<ClassDailyEvent> list = classDailyEventService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
