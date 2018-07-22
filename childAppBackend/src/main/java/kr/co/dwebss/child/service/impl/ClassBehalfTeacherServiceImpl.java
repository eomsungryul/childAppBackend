package kr.co.dwebss.child.service.impl;

import kr.co.dwebss.child.dao.ClassBehalfTeacherMapper;
import kr.co.dwebss.child.model.ClassBehalfTeacher;
import kr.co.dwebss.child.service.ClassBehalfTeacherService;
import kr.co.dwebss.child.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by 엄성렬 on 2018/07/22.
 */
@Service
@Transactional
public class ClassBehalfTeacherServiceImpl extends AbstractService<ClassBehalfTeacher> implements ClassBehalfTeacherService {
    @Resource
    private ClassBehalfTeacherMapper classBehalfTeacherMapper;

}
