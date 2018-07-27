package kr.co.dwebss.child.web;

import kr.co.dwebss.child.core.Result;
import kr.co.dwebss.child.core.ResultCode;
import kr.co.dwebss.child.core.ResultGenerator;
import kr.co.dwebss.child.model.Child;
import kr.co.dwebss.child.model.ClassVO;
import kr.co.dwebss.child.model.User;
import kr.co.dwebss.child.service.ChildService;
import kr.co.dwebss.child.service.ClassService;
import kr.co.dwebss.child.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by 엄성렬 on 2018/07/22.
*/
@RestController
@RequestMapping("/user")
public class UserController {
	
    @Resource
    private UserService userService;

    @Resource
    private ClassService classService;

    @Resource
    private ChildService childService;

    @PostMapping
    public Result add(@RequestBody User user) {
        userService.save(user);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody User user, @PathVariable Integer id) {
    	user.setUserId(id);
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/email/{email:.+}")
    public Result detail(@PathVariable String email) {
    	User user =  new User();
    	user.setUserEmail(email);
    	
        User res = userService.selectUser(user);
        ResultCode resCode = ResultCode.SUCCESS;
        String message = "SUCCESS";
        
        if (res ==null) {
        	resCode = ResultCode.NOT_FOUND;
            message = "NOTFOUND";
        }else {
	    	Child chVO = new Child();
	    	chVO.setParentPhone(res.getUserPhone());	
	        user.setMyChildList(childService.selectChild(chVO));
	    	ClassVO clVO = new ClassVO();
	    	clVO.setTeacherUserId(res.getUserId());
	        user.setMyClassList(classService.selectClass(clVO));
	    	clVO = new kr.co.dwebss.child.model.ClassVO();
	    	clVO.setCenterId(res.getCenterId());
	        user.setCenterClassList(classService.selectClass(clVO));
        }
        return ResultGenerator.genSuccessResult(res).setCode(resCode).setMessage(message);
    }
    
    @PutMapping("/phone/{phone}")
    public Result updateUser(@RequestBody User user, @PathVariable String phone) {

    	user.setUserPhone(phone);
    	User param = new User();
    	param.setUserPhone(phone);
        User res = userService.selectUser(param);
        
        if (res ==null) {
        	user.setUserLoginId(phone);
        	userService.save(user);
        	user = userService.selectUser(param);
        }else {
        	userService.updateUser(user);
        	user = userService.selectUser(param);
        	
	    	Child chVO = new Child();
	    	chVO.setParentPhone(res.getUserPhone());	
	        user.setMyChildList(childService.selectChild(chVO));
	    	ClassVO clVO = new ClassVO();
	    	clVO.setTeacherUserId(res.getUserId());
	        user.setMyClassList(classService.selectClass(clVO));
	    	clVO = new kr.co.dwebss.child.model.ClassVO();
	    	clVO.setCenterId(res.getCenterId());
	        user.setCenterClassList(classService.selectClass(clVO));
        }
        
        return ResultGenerator.genSuccessResult(user);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<User> list = userService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
