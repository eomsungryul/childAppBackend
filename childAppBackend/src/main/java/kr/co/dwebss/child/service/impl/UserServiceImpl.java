package kr.co.dwebss.child.service.impl;

import kr.co.dwebss.child.dao.UserMapper;
import kr.co.dwebss.child.model.User;
import kr.co.dwebss.child.service.UserService;
import kr.co.dwebss.child.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by 엄성렬 on 2018/07/22.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

	@Override
	public User selectUser(User user) {
		return userMapper.selectUser(user);
	}

}
