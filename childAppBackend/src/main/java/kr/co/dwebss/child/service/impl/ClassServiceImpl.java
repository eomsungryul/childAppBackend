package kr.co.dwebss.child.service.impl;

import kr.co.dwebss.child.dao.ClassMapper;
import kr.co.dwebss.child.model.Class;
import kr.co.dwebss.child.service.ClassService;
import kr.co.dwebss.child.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by 엄성렬 on 2018/07/22.
 */
@Service
@Transactional
public class ClassServiceImpl extends AbstractService<Class> implements ClassService {
    @Resource
    private ClassMapper classMapper;

}
