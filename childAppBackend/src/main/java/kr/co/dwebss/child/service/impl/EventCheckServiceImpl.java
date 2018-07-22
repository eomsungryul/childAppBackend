package kr.co.dwebss.child.service.impl;

import kr.co.dwebss.child.dao.EventCheckMapper;
import kr.co.dwebss.child.model.EventCheck;
import kr.co.dwebss.child.service.EventCheckService;
import kr.co.dwebss.child.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by 엄성렬 on 2018/07/22.
 */
@Service
@Transactional
public class EventCheckServiceImpl extends AbstractService<EventCheck> implements EventCheckService {
    @Resource
    private EventCheckMapper eventCheckMapper;

}
