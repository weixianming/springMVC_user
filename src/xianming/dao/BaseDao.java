package xianming.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import xianming.model.Pager;
import xianming.model.SystemContext;

public class BaseDao<T> extends HibernateDaoSupport implements IBaseDao<T> {

	private Class<T> clz;
	
	@Resource(name="sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	public Class<T> getClz() {
		if(clz==null) {
			//获取泛型的Class对象
			clz = ((Class<T>)
					(((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
		}
		return clz;
	}
	
	@Override
	public void add(T t) {
		this.getHibernateTemplate().save(t);
	}
	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	@Override
	public void delete(int id) {
		this.getHibernateTemplate().delete(this.load(id));
	}

	@Override
	public T load(int id) {
		return this.getHibernateTemplate().get(getClz(), id);
	}
	
	private Query setParamterQuery(String hql,Object[] args){
		Query q = this.getSession().createQuery(hql);
		if(args!=null){
			for(int i=0;i<args.length;i++){
				q.setParameter(i, args[i]);
			}
		}
		return q;
	}

	@Override
	public List<T> list(String hql,Object[] args) {
		Query q = setParamterQuery(hql, args);
		return q.list();
	}

	@Override
	public List<T> list(String hql) {
		return this.list(hql, null);
	}

	@Override
	public List<T> list(String hql, Object obj) {
		return this.list(hql, new Object[]{obj});
	}

	@SuppressWarnings("unchecked")
	@Override
	public T loadByName(String hql,String str) {
		Query q = setParamterQuery(hql, new Object[]{str});
		return (T)q.uniqueResult();
	}

	@Override
	public Pager<T> find(String hql, Object[] args) {
		int pageSize = SystemContext.getPageSize();
		int pageOffset = SystemContext.getPageOffset();
		if(pageSize<=0) pageSize = 10;
		if(pageOffset<0) pageOffset = 0; 
		Query q = this.setParamterQuery(hql, args);
		q.setFirstResult(pageOffset).setMaxResults(pageSize);
		Query qc = this.setParamterQuery(getCountsql(hql), args);
		Pager<T> page = new Pager<T>();
		page.setPageOffset(pageOffset);
		page.setPageSize(pageSize);
		page.setTotalRecord((long)qc.uniqueResult());
		List<T> list = q.list();
		page.setDatas(list);
		return page;
	}
	
	private String getCountsql(String hql){
		String s = hql.substring(0,hql.indexOf("from"));
		if(s==null||s.trim().equals("")){
			hql = "select count(*)" + hql;
		}else{
			hql = hql.replace(s, "select count(*)");
		}
		hql.replace("fetch", " ");
		return hql;
	}

	@Override
	public Pager<T> find(String hql, Object obj) {
		return this.find(hql, new Object[]{obj});
	}

	@Override
	public Pager<T> find(String hql) {
		return this.find(hql,null);
	}

}
