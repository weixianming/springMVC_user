package xianming.dao;

import java.util.List;

import xianming.model.Pager;

public interface IBaseDao<T> {
	
	public void add(T t);
	
	public void update(T t);
	
	public void delete(int id);
	
	public T load(int id);
	
	public List<T> list(String hql,Object[] args);
	
	public List<T> list(String hql);
	
	public List<T> list(String hql,Object obj);
	
	public T loadByName(String hql,String str);
	
	public Pager<T> find(String hql,Object[] args);
	public Pager<T> find(String hql,Object obj);
	public Pager<T> find(String hql);
}
