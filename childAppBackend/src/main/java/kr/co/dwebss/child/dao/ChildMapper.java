package kr.co.dwebss.child.dao;

import java.util.List;

import kr.co.dwebss.child.core.Mapper;
import kr.co.dwebss.child.model.Child;

public interface ChildMapper extends Mapper<Child> {

	List<Child> selectChild(Child vo);
}