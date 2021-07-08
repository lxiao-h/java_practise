package com.hebtu.service;
// 该包表示能够提供什么服务

import java.util.Set;
import com.hebtu.dao.ConcreteDao;
import com.hebtu.dao.DatabaseDAO;
import com.hebtu.model.User;

public class DatabaseService {
	
	DatabaseDAO sd = new ConcreteDao();//多态
	
	public int add(Object obj)
	{
		return this.sd.add(obj);
	}
	
	public int delete(Object obj)
	{
		return this.sd.delete(obj);
	}
	
	public int update(Object obj)
	{
		return this.sd.update(obj);
	}
	
	public Object find(Object obj,String name)
	{
		return this.sd.find(obj,name);
	}
	
	public Set<Object> findAll(Object obj)
	{
		return this.sd.findAll(obj);
	}

}


