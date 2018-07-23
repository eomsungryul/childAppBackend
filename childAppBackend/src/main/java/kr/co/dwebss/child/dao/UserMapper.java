package kr.co.dwebss.child.dao;

import kr.co.dwebss.child.core.Mapper;
import kr.co.dwebss.child.model.User;

public interface UserMapper extends Mapper<User> {

	User selectUser(User user);
	
	
}