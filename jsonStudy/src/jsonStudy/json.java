package jsonStudy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class json {

	public static void main(String[] args) {
		
		Map map = new HashMap();
		
		map.put("name", "³ÉÁú");
		map.put("age", 60);
		map.put("gender", "ÄĞ");
		
		User user = new User();
		user.setPassword("123456");
		user.setUsername("admin");
		
		List list = new ArrayList();
		list.add("ÄşÄş");
		list.add("ÀîÀî");
		list.add("³Â³Â");			
		
		
		map.put("user", user);
		map.put("list", list);
		
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);

	}

}
