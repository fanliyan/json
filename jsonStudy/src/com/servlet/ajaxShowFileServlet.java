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

public class ajaxShowFileServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		    doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {	
		     DB  db = new DB();
		     Map map = new HashMap();
		     try {
				db.getConnection("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/crm", "root", "fanliyan");
				 List list = db.query("select id, customername, contacttime, companyaddress from user order by contacttime limit ?, ?", 4, 0, 5);
				 		 
				 map.put("result", 1);
				 map.put("list", list);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				map.put("result", -1);
			} catch (SQLException e) {
				e.printStackTrace();
				map.put("result", -1);
			}
		     String JsonStr = JSON.toJSONString(map);
		     resp.setContentType("text/html; charset=GBK");
		     resp.getWriter().print(JsonStr);
	}
   
	 
}
