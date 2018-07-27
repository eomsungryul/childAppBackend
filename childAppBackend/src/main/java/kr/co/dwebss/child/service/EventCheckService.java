package kr.co.dwebss.child.service;
import kr.co.dwebss.child.model.EventCheck;

import java.util.List;

import kr.co.dwebss.child.core.Service;


/**
 * Created by 엄성렬 on 2018/07/22.
 */
public interface EventCheckService extends Service<EventCheck> {

	List<EventCheck> selectEventCheckListForParent(EventCheck param);

	List<EventCheck> selectEventCheckListForTeacher(EventCheck param);

	List<EventCheck> selectEventCheckListForCenter(EventCheck param);

	List<EventCheck> selectEventCheck(EventCheck eventCheck);

	List<EventCheck> selectAlertAlarmChildList();
}
