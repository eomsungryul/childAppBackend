package kr.co.dwebss.child.web;

import kr.co.dwebss.child.core.Result;
import kr.co.dwebss.child.core.ResultGenerator;
import kr.co.dwebss.child.model.CommonCode;
import kr.co.dwebss.child.service.CommonCodeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by 엄성렬 on 2018/07/22.
*/
@RestController
@RequestMapping("/code")
public class CommonCodeController {
    @Resource
    private CommonCodeService commonCodeService;

    @PostMapping
    public Result add(@RequestBody CommonCode commonCode) {
        commonCodeService.save(commonCode);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        commonCodeService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody CommonCode commonCode) {
        commonCodeService.update(commonCode);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        CommonCode commonCode = commonCodeService.findById(id);
        return ResultGenerator.genSuccessResult(commonCode);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<CommonCode> list = commonCodeService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
