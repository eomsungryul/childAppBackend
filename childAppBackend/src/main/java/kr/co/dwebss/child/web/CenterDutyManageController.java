package kr.co.dwebss.child.web;

import kr.co.dwebss.child.core.Result;
import kr.co.dwebss.child.core.ResultGenerator;
import kr.co.dwebss.child.model.CenterDutyManage;
import kr.co.dwebss.child.service.CenterDutyManageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by 엄성렬 on 2018/07/22.
*/
@RestController
@RequestMapping("/center/duty/manage")
public class CenterDutyManageController {
    @Resource
    private CenterDutyManageService centerDutyManageService;

    @PostMapping
    public Result add(@RequestBody CenterDutyManage centerDutyManage) {
        centerDutyManageService.save(centerDutyManage);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        centerDutyManageService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody CenterDutyManage centerDutyManage) {
        centerDutyManageService.update(centerDutyManage);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        CenterDutyManage centerDutyManage = centerDutyManageService.findById(id);
        return ResultGenerator.genSuccessResult(centerDutyManage);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<CenterDutyManage> list = centerDutyManageService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
