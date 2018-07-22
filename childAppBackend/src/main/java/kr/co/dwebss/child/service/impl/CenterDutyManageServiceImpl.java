package kr.co.dwebss.child.service.impl;

import kr.co.dwebss.child.dao.CenterDutyManageMapper;
import kr.co.dwebss.child.model.CenterDutyManage;
import kr.co.dwebss.child.service.CenterDutyManageService;
import kr.co.dwebss.child.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by 엄성렬 on 2018/07/22.
 */
@Service
@Transactional
public class CenterDutyManageServiceImpl extends AbstractService<CenterDutyManage> implements CenterDutyManageService {
    @Resource
    private CenterDutyManageMapper centerDutyManageMapper;

}
