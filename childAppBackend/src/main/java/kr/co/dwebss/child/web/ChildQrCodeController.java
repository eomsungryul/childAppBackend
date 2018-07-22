package kr.co.dwebss.child.web;

import kr.co.dwebss.child.core.Result;
import kr.co.dwebss.child.core.ResultGenerator;
import kr.co.dwebss.child.model.ChildQrCode;
import kr.co.dwebss.child.service.ChildQrCodeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by 엄성렬 on 2018/07/22.
*/
@RestController
@RequestMapping("/child/qr/code")
public class ChildQrCodeController {
    @Resource
    private ChildQrCodeService childQrCodeService;

    @PostMapping
    public Result add(@RequestBody ChildQrCode childQrCode) {
        childQrCodeService.save(childQrCode);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        childQrCodeService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody ChildQrCode childQrCode) {
        childQrCodeService.update(childQrCode);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        ChildQrCode childQrCode = childQrCodeService.findById(id);
        return ResultGenerator.genSuccessResult(childQrCode);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<ChildQrCode> list = childQrCodeService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
