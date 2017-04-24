package xianming.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import xianming.dao.IUserDao;
import xianming.model.Pager;
import xianming.model.User;

@Service("userService")
public class UserService implements IUserService{
	
	private IUserDao userDao;
	
	public IUserDao getUserDao() {
		return userDao;
	}
	
	@Resource
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void add(User user) {
		userDao.add(user);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void delete(int id) {
		userDao.delete(id);
	}

	@Override
	public User load(int id) {
		return userDao.load(id);
	}

	@Override
	public List<User> list() {
		return userDao.list("from User");
	}

	@Override
	public User login(String username) {
		return userDao.loadByName("select u from User u where u.username=?", username);
	}

	@Override
	public Pager<User> find() {
		String hql = "from User";
		return userDao.find(hql);
	}
}
