package kr.co.dwebss.child.web;

import kr.co.dwebss.child.core.Result;
import kr.co.dwebss.child.core.ResultGenerator;
import kr.co.dwebss.child.model.ClassBehalfTeacher;
import kr.co.dwebss.child.service.ClassBehalfTeacherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by 엄성렬 on 2018/07/22.
*/
@RestController
@RequestMapping("/class/behalf/teacher")
public class ClassBehalfTeacherController {
    @Resource
    private ClassBehalfTeacherService classBehalfTeacherService;

    @PostMapping
    public Result add(@RequestBody ClassBehalfTeacher classBehalfTeacher) {
        classBehalfTeacherService.save(classBehalfTeacher);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        classBehalfTeacherService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody ClassBehalfTeacher classBehalfTeacher) {
        classBehalfTeacherService.update(classBehalfTeacher);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        ClassBehalfTeacher classBehalfTeacher = classBehalfTeacherService.findById(id);
        return ResultGenerator.genSuccessResult(classBehalfTeacher);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<ClassBehalfTeacher> list = classBehalfTeacherService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
