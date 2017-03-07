package com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.dao.DB;

public class ajaxLoginServlet extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2415917721378040025L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
           doPost(request, response);
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
 
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		/**
		 *1:登录成功
		 *-1:没有此用户
		 *-2:密码错误
		 */
		int result = 0;
		
		DB db = new DB();
		 
		try {
			db.getConnection("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/crm", "root", "fanliyan");
			List list = db.query("select customername, password from user where customername = ?", 2, username);
			if(list != null && !list.isEmpty()){
				Object[] array = (Object[])list.get(0);
				String passwordDB = (String)array[1];
				if(passwordDB.equals(password)){
					result = 1;
				}else{
					result = -2;
				}
			}else{
				result = -1;
			}
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
						
		Map map = new HashMap();
		
		map.put("result", 1);
		map.put("loginResult", result);
		
//		String JSONstr = JSON.toJSONString(map);
//		response.getWriter().print(JSONstr);
	}

}
