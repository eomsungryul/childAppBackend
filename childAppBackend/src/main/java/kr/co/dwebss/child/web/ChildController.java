package kr.co.dwebss.child.web;

import kr.co.dwebss.child.core.Result;
import kr.co.dwebss.child.core.ResultGenerator;
import kr.co.dwebss.child.model.Child;
import kr.co.dwebss.child.service.ChildService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.time.LocalDate;
import java.util.List;

/**
* Created by 엄성렬 on 2018/07/22.
*/
@RestController
@RequestMapping("/child")
public class ChildController {
    @Resource
    private ChildService childService;

    @PostMapping
    public Result add(@RequestBody Child child) {
        childService.save(child);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        childService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody Child child) {
        childService.update(child);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        Child child = childService.findById(id);
        return ResultGenerator.genSuccessResult(child);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Child> list = childService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
    
    @GetMapping("/child/parent/{userLoginid}/{date}")
    public Result getMyChildList(@PathVariable Integer userLoginid, @PathVariable @DateTimeFormat(pattern = "yyyyMMdd") LocalDate date) {
    	
    	
    	// 1. 부모님 의 userLoginId로 내 아이를 먼저 가져오는 쿼리를 실행  userLoginId으로 검색
    	
    	// 2. 가져온 아이들 LIST를 한 로우씩 ChildEventLIst와 classChildList를 가져옴
    	// 2-1. ChildEventLIst는 현재 List의 childId와 날짜를 이용하여 가져옴
    	
    	// 2-2. classChildList는 현재 우리 아이의 class_id를 이용하여 다른 아이들 List를 가져온다
    	// 2-2-1. 다른아이들 List를 한 로우 씩  ChildEventLIst 가져온다
    	
    	
    	//배치
    	//오늘자 이벤트 뷰
    	//나와 같은반 애들이 동일한 이벤트를 시작헀는데 내 애가 5분(임계치) 동안 이벤트를 하지못했음
    	//우선 선생님에게 알람을 보냄
    	//10분 뒤에는 엄니에게 이벤트를 보냄 
    	
    	
    	return ResultGenerator.genSuccessResult();
    }
}
