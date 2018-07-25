package kr.co.dwebss.child.dao;

import java.util.List;

import kr.co.dwebss.child.core.Mapper;
import kr.co.dwebss.child.model.ClassVO;

public interface ClassMapper extends Mapper<ClassVO> {

	List<ClassVO> selectClass(ClassVO vo);
}