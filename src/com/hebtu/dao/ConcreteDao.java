package com.hebtu.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.hebtu.util.JDBCUtils;

public class ConcreteDao implements DatabaseDAO {
	Class c = null;

	@Override
	public int add(Object obj) {
		Connection con = null;
		PreparedStatement ps = null;
		int n = 0;
		con = JDBCUtils.getConnection();

		c = obj.getClass();// 获取obj的Class

		StringBuffer sb1 = new StringBuffer("insert into " + c.getSimpleName() + "("); // 通过反射获取类名映射表名
		StringBuffer sb2 = new StringBuffer(" values("); // 注意前面要多加一个空格 否则sql将连在一起

		Field[] field = c.getDeclaredFields(); // 获取对象的属性数组
		for (int i = 0; i < field.length; i++) { // 遍历属性构造SQL语句
			if (i != field.length - 1) {
				sb1.append(field[i].getName()).append(",");
				sb2.append("?,");
			} else {
				sb1.append(field[i].getName()).append(")");
				sb2.append("?);");
			}
		}
		String sql = sb1.append(sb2).toString();
		try {
			ps = con.prepareStatement(sql);
			for (int i = 0; i < field.length; i++) {
				field[i].setAccessible(true); // 设置属性的可访问性，可以访问私有属性
				try { // 通过Field的get(Object)方法获取Object对象的属性值
					ps.setObject(i + 1, field[i].get(obj)); // 对预编译的SQL语句中的？进行赋值
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (ps.executeUpdate() == 1) {
				n = 1;// 执行SQL
			}
		}
		/********** End **********/
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.free(null, ps, con);
		}
		return n;
	}

	@Override
	public int delete(Object obj) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		c = obj.getClass();
		Field[] field = c.getDeclaredFields();
		int n = 0;
		try {
			con = JDBCUtils.getConnection();
			StringBuffer sb1 = new StringBuffer("update ").append(c.getSimpleName()).append(" set ");

			for (int i = 1; i < field.length; i++) {
				if (i != field.length - 1) { // 判断是否为最后一个属性，若不是则后增加逗号
					sb1.append(field[i].getName()).append("=?,");
				} else { // 若为最后一个属性则添加 where
					sb1.append(field[i].getName()).append("=? where ");
				}
			}
			// 默认第一个属性为主键，切更改时通过第一个属性进行更改
			sb1.append(field[0].getName() + "=?");
			String sql = sb1.toString() + ";";
//			System.out.println(sql);
			ps = con.prepareStatement(sql);

			for (int i = 1; i < field.length; i++) {
				field[i].setAccessible(true);// 设置可以访问私有属性
				ps.setObject(i, field[i].get(obj));// 对预编译的SQL语句中的 ? 进行赋值
			}
			field[0].setAccessible(true);
			ps.setObject(field.length, field[0].get(obj));
			n = ps.executeUpdate();// 执行sql语句

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JDBCUtils.free(null, ps, con);
		}

		return n;
	}

	@Override
	public int update(Object obj) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		c = obj.getClass();
		Field[] field = c.getDeclaredFields();
		int n = 0;
		try {
			con = JDBCUtils.getConnection();
			StringBuffer sb1 = new StringBuffer("update ").append(c.getSimpleName()).append(" set ");

			for (int i = 1; i < field.length; i++) {
				if (i != field.length - 1) { // 判断是否为最后一个属性，若不是则后增加逗号
					sb1.append(field[i].getName()).append("=?,");
				} else { // 若为最后一个属性则添加 where
					sb1.append(field[i].getName()).append("=? where ");
				}
			}
			// 默认第一个属性为主键，切更改时通过第一个属性进行更改
			sb1.append(field[0].getName() + "=?");
			String sql = sb1.toString() + ";";
//			System.out.println(sql);
			ps = con.prepareStatement(sql);

			for (int i = 1; i < field.length; i++) {
				field[i].setAccessible(true);// 设置可以访问私有属性
				ps.setObject(i, field[i].get(obj));// 对预编译的SQL语句中的 ? 进行赋值
			}
			field[0].setAccessible(true);
			ps.setObject(field.length, field[0].get(obj));
			n = ps.executeUpdate();// 执行sql语句

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JDBCUtils.free(null, ps, con);
		}

		return n;
	}

	@Override
	public Object find(Object obj, String name) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		c = obj.getClass();
		Field[] field = c.getDeclaredFields();
		ResultSet n = null;
		Set<Object> set = null;
		Object o = null;
		try {
			con = JDBCUtils.getConnection();
//			String s="select * from User where uname=?";
			StringBuffer sb1 = new StringBuffer("select * from ").append(c.getSimpleName()).append(" where ");
			// 姓名 ，切更改时通过第一个属性进行更改
			sb1.append(field[1].getName() + "=?");
			String sql = sb1.toString() + ";";
//			System.out.println("查询语句" + sql);
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			n = ps.executeQuery();// 执行sql语句
			ResultSetMetaData rst = n.getMetaData();

			int count = rst.getColumnCount();
			while (n.next()) {
				o = c.newInstance(); // 反射机制

				for (int i = 1; i <= count; i++) {
					String colName = rst.getColumnName(i); // 获得列名
					Object value = n.getObject(i); // 取出表中的数据
					Field field1 = c.getDeclaredField(colName); // 获取对象属性
					field1.setAccessible(true); // 更改权限
					field1.set(o, value); // 给私有属性赋值
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JDBCUtils.free(null, ps, con);
		}
		return o;
	}

	@Override
	public Set<Object> findAll(Object obj) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<Object> set = null;

		try {
			con = JDBCUtils.getConnection();
			Class c = obj.getClass();
			String sql = "select * from " + c.getSimpleName();
			ps = con.prepareStatement(sql);
			set = new HashSet<Object>();
			rs = ps.executeQuery();
			ResultSetMetaData rst = rs.getMetaData();
			int count = rst.getColumnCount();
			while (rs.next()) {
				Object o = c.newInstance(); // 反射机制
				for (int i = 1; i <= count; i++) {
					String colName = rst.getColumnName(i); // 获得列名
					Object value = rs.getObject(i); // 取出表中的数据
					Field field = c.getDeclaredField(colName); // 获取对象属性
					field.setAccessible(true); // 更改权限
					field.set(o, value); // 给私有属性赋值
				}
				set.add(o);
			}
//			System.out.println(set.size() + "---添加");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JDBCUtils.free(rs, ps, con);
		}
		return set;
	}

}
