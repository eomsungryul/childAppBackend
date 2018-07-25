package kr.co.dwebss.child.service;
import kr.co.dwebss.child.model.Child;

import java.util.List;

import kr.co.dwebss.child.core.Service;


/**
 * Created by 엄성렬 on 2018/07/22.
 */
public interface ChildService extends Service<Child> {

	List<Child> selectChild(Child chVO);

}
