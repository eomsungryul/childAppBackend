package kr.co.dwebss.child.service.impl;

import kr.co.dwebss.child.dao.EventCheckMapper;
import kr.co.dwebss.child.model.EventCheck;
import kr.co.dwebss.child.service.EventCheckService;
import kr.co.dwebss.child.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;


/**
 * Created by 엄성렬 on 2018/07/22.
 */
@Service
@Transactional
public class EventCheckServiceImpl extends AbstractService<EventCheck> implements EventCheckService {
    @Resource
    private EventCheckMapper eventCheckMapper;

	@Override
	public List<EventCheck> selectEventCheckListForParent(EventCheck param) {
		return eventCheckMapper.selectEventCheckListForParent(param);
	}

	@Override
	public List<EventCheck> selectEventCheckListForTeacher(EventCheck param) {
		return eventCheckMapper.selectEventCheckListForTeacher(param);
	}

	@Override
	public List<EventCheck> selectEventCheckListForCenter(EventCheck param) {
		return eventCheckMapper.selectEventCheckListForCenter(param);
	}

	@Override
	public List<EventCheck> selectEventCheck(EventCheck eventCheck) {
		return eventCheckMapper.selectEventCheck(eventCheck);
	}

	@Override
	public List<EventCheck> selectAlertAlarmChildList() {
		return eventCheckMapper.selectAlertAlarmChildList();
	}

}
