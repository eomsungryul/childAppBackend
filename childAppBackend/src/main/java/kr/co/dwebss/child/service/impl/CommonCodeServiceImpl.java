package kr.co.dwebss.child.service.impl;

import kr.co.dwebss.child.dao.CommonCodeMapper;
import kr.co.dwebss.child.model.CommonCode;
import kr.co.dwebss.child.service.CommonCodeService;
import kr.co.dwebss.child.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by 엄성렬 on 2018/07/22.
 */
@Service
@Transactional
public class CommonCodeServiceImpl extends AbstractService<CommonCode> implements CommonCodeService {
    @Resource
    private CommonCodeMapper commonCodeMapper;

	@Override
	public CommonCode selectCode(Integer eventCheckType) {
		return commonCodeMapper.selectCode(eventCheckType);
	}

}
