package kr.co.dwebss.child.web;

import kr.co.dwebss.child.core.Result;
import kr.co.dwebss.child.core.ResultGenerator;
import kr.co.dwebss.child.model.CommonCodeCategory;
import kr.co.dwebss.child.service.CommonCodeCategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by 엄성렬 on 2018/07/22.
*/
@RestController
@RequestMapping("/common/code/category")
public class CommonCodeCategoryController {
    @Resource
    private CommonCodeCategoryService commonCodeCategoryService;

    @PostMapping
    public Result add(@RequestBody CommonCodeCategory commonCodeCategory) {
        commonCodeCategoryService.save(commonCodeCategory);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        commonCodeCategoryService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody CommonCodeCategory commonCodeCategory) {
        commonCodeCategoryService.update(commonCodeCategory);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        CommonCodeCategory commonCodeCategory = commonCodeCategoryService.findById(id);
        return ResultGenerator.genSuccessResult(commonCodeCategory);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<CommonCodeCategory> list = commonCodeCategoryService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
