package kr.co.dwebss.child.service.impl;

import kr.co.dwebss.child.dao.CommonCodeCategoryMapper;
import kr.co.dwebss.child.model.CommonCodeCategory;
import kr.co.dwebss.child.service.CommonCodeCategoryService;
import kr.co.dwebss.child.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by 엄성렬 on 2018/07/22.
 */
@Service
@Transactional
public class CommonCodeCategoryServiceImpl extends AbstractService<CommonCodeCategory> implements CommonCodeCategoryService {
    @Resource
    private CommonCodeCategoryMapper commonCodeCategoryMapper;

}
