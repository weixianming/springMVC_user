package xianming.dao;


import org.springframework.stereotype.Repository;

import xianming.model.User;

@Repository("userDao")
public class UserDao extends BaseDao<User> implements IUserDao {

}
