package kr.co.dwebss.child.service;
import kr.co.dwebss.child.model.User;
import kr.co.dwebss.child.core.Service;


/**
 * Created by 엄성렬 on 2018/07/22.
 */
public interface UserService extends Service<User> {

	User selectUser(User user);

}
