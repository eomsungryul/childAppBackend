package kr.co.dwebss.child.dao;

import java.util.List;

import kr.co.dwebss.child.core.Mapper;
import kr.co.dwebss.child.model.EventCheck;

public interface EventCheckMapper extends Mapper<EventCheck> {

	List<EventCheck> selectEventCheckListForParent(EventCheck param);

	List<EventCheck> selectEventCheckListForTeacher(EventCheck param);

	List<EventCheck> selectEventCheckListForCenter(EventCheck param);
}