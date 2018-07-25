package kr.co.dwebss.child.service;
import kr.co.dwebss.child.model.ClassDailyEvent;

import java.util.List;

import kr.co.dwebss.child.core.Service;


/**
 * Created by 엄성렬 on 2018/07/22.
 */
public interface ClassDailyEventService extends Service<ClassDailyEvent> {

	List<ClassDailyEvent> selectClassEvent(ClassDailyEvent cdeVO);

}
