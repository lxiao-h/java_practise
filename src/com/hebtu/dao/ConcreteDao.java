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

		c = obj.getClass();// ��ȡobj��Class

		StringBuffer sb1 = new StringBuffer("insert into " + c.getSimpleName() + "("); // ͨ�������ȡ����ӳ�����
		StringBuffer sb2 = new StringBuffer(" values("); // ע��ǰ��Ҫ���һ���ո� ����sql������һ��

		Field[] field = c.getDeclaredFields(); // ��ȡ�������������
		for (int i = 0; i < field.length; i++) { // �������Թ���SQL���
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
				field[i].setAccessible(true); // �������ԵĿɷ����ԣ����Է���˽������
				try { // ͨ��Field��get(Object)������ȡObject���������ֵ
					ps.setObject(i + 1, field[i].get(obj)); // ��Ԥ�����SQL����еģ����и�ֵ
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (ps.executeUpdate() == 1) {
				n = 1;// ִ��SQL
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
				if (i != field.length - 1) { // �ж��Ƿ�Ϊ���һ�����ԣ�������������Ӷ���
					sb1.append(field[i].getName()).append("=?,");
				} else { // ��Ϊ���һ����������� where
					sb1.append(field[i].getName()).append("=? where ");
				}
			}
			// Ĭ�ϵ�һ������Ϊ�������и���ʱͨ����һ�����Խ��и���
			sb1.append(field[0].getName() + "=?");
			String sql = sb1.toString() + ";";
//			System.out.println(sql);
			ps = con.prepareStatement(sql);

			for (int i = 1; i < field.length; i++) {
				field[i].setAccessible(true);// ���ÿ��Է���˽������
				ps.setObject(i, field[i].get(obj));// ��Ԥ�����SQL����е� ? ���и�ֵ
			}
			field[0].setAccessible(true);
			ps.setObject(field.length, field[0].get(obj));
			n = ps.executeUpdate();// ִ��sql���

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
				if (i != field.length - 1) { // �ж��Ƿ�Ϊ���һ�����ԣ�������������Ӷ���
					sb1.append(field[i].getName()).append("=?,");
				} else { // ��Ϊ���һ����������� where
					sb1.append(field[i].getName()).append("=? where ");
				}
			}
			// Ĭ�ϵ�һ������Ϊ�������и���ʱͨ����һ�����Խ��и���
			sb1.append(field[0].getName() + "=?");
			String sql = sb1.toString() + ";";
//			System.out.println(sql);
			ps = con.prepareStatement(sql);

			for (int i = 1; i < field.length; i++) {
				field[i].setAccessible(true);// ���ÿ��Է���˽������
				ps.setObject(i, field[i].get(obj));// ��Ԥ�����SQL����е� ? ���и�ֵ
			}
			field[0].setAccessible(true);
			ps.setObject(field.length, field[0].get(obj));
			n = ps.executeUpdate();// ִ��sql���

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
			// ���� ���и���ʱͨ����һ�����Խ��и���
			sb1.append(field[1].getName() + "=?");
			String sql = sb1.toString() + ";";
//			System.out.println("��ѯ���" + sql);
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			n = ps.executeQuery();// ִ��sql���
			ResultSetMetaData rst = n.getMetaData();

			int count = rst.getColumnCount();
			while (n.next()) {
				o = c.newInstance(); // �������

				for (int i = 1; i <= count; i++) {
					String colName = rst.getColumnName(i); // �������
					Object value = n.getObject(i); // ȡ�����е�����
					Field field1 = c.getDeclaredField(colName); // ��ȡ��������
					field1.setAccessible(true); // ����Ȩ��
					field1.set(o, value); // ��˽�����Ը�ֵ
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
				Object o = c.newInstance(); // �������
				for (int i = 1; i <= count; i++) {
					String colName = rst.getColumnName(i); // �������
					Object value = rs.getObject(i); // ȡ�����е�����
					Field field = c.getDeclaredField(colName); // ��ȡ��������
					field.setAccessible(true); // ����Ȩ��
					field.set(o, value); // ��˽�����Ը�ֵ
				}
				set.add(o);
			}
//			System.out.println(set.size() + "---���");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			JDBCUtils.free(rs, ps, con);
		}
		return set;
	}

}
