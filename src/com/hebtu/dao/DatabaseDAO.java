package com.hebtu.dao;

import java.util.Set;

public interface DatabaseDAO {

	public int add(Object obj);

	public int delete(Object obj);

	public int update(Object obj);

	public Object find(Object obj, String name);

	public Set<Object> findAll(Object obj);

}
