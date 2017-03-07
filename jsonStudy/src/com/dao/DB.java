package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class DB {
   
	private Connection conn;
	
	public void getConnection(String driver, String url, String username, String password) throws ClassNotFoundException, SQLException{
		Class.forName(driver);
		conn = DriverManager.getConnection(url, username, password);
		if(conn != null && !conn.isClosed()){
			System.out.println("数据库连接的成功！");
		}
	}
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List query(String sql, int columnCount, Object... objects) throws SQLException{
		List list = new ArrayList();
		PreparedStatement ps = conn.prepareStatement(sql);
		if(objects != null && objects.length > 0){
			for(int i = 0; i < objects.length; i++){
				ps.setObject(i+1, objects[i]);
			}
		}	
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Object[] array = new  Object[columnCount];
			for(int i = 0; i < columnCount; i++){
				array[i] = rs.getObject(i+1);
			}
			list.add(array);
		}
		rs.close();
		ps.close();
		return list;
	}
	
	public int update(String sql, Object ...objects) throws SQLException{
		PreparedStatement ps = conn.prepareStatement(sql);
		if(objects != null && objects.length > 0){
			for(int i = 0; i < objects.length; i++){
				ps.setObject(i+1, objects[i]);
			}
		}
		int i = ps.executeUpdate();
		ps.close();
		return i;
	}
	
	public void close() throws SQLException{
		if(conn != null && !conn.isClosed()){
			conn.close();
		}
	}
	

	
	
	


	
	
	
	public static void main(String[] args) {
		DB db = new DB();
		try {
			db.getConnection("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/crm", "root", "fanliyan");
			int  i = db.update("update user set password = 'fanliyan' where customername = ?", "炎目");
			System.out.println(i);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}