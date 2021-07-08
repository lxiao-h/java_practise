package com.hebtu.util;
// �ð���Ź�����
// JDBCUtils�ṩ�����ݿ�����Ӻ��ͷŹ���

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.hebtu.model.JDBCInfo;

public class JDBCUtils {
	
	public static Connection getConnection()
	{
		JDBCInfo info = new XmlConfigReader().getJdbcInfo();		
		Connection con = null ;		
		try {		
			Class.forName(info.getDrivername());
			String url = info.getUrl();
			String username =  info.getUsername();
			String password = info.getPassword();
			con = DriverManager.getConnection(url,username, password);
//			System.out.println("���ݿ����ӳɹ�");
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return con ;		
	}

	/**
	 * �ر�����
	 */
	public static void free(ResultSet rs, Statement sta , Connection con)
	{
		try {
			if(null != rs)
			{
				rs.close();
				rs = null ;
			}
			
			if(null != sta)
			{
				sta.close();
				sta = null ;
			}
			
			if(null != con)
			{
				con.close();
				con = null ;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}




