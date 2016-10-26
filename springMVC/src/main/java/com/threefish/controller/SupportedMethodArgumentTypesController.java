package com.threefish.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("argtypes")
//@SessionAttributes("sessionKey1")
public class SupportedMethodArgumentTypesController {
	
	@RequestMapping("arg1")
	@ResponseBody 
	public String arg1(@RequestParam(name="arg1",required=false) String param){
		return param;
	}
	
	@RequestMapping("arg2/{id}/{action}")
	@ResponseBody 
	public String arg2(@PathVariable("id") String id,@PathVariable("action") String action){
		return id+" do " + action;
	}
	
	@RequestMapping("arg3")
	@ResponseBody 
	public String arg3(@RequestHeader("myHeader") String headerParam){
		return "myHeader: "+headerParam;
	}
	
	@RequestMapping(value="arg4",method=RequestMethod.POST)
	@ResponseBody 
	public String arg4(@RequestBody byte[] reqBody, Map map){
		String s = new String(reqBody);
		return s;
	}
	
	// @ModelAttribute
	@ModelAttribute("init")
	public Map modelMethod(HttpSession session){
		Map map = new HashMap<String,Object>();
		map.put("key1", "value1");
		return map;
	}
	
	@RequestMapping(value="arg5")
	@ResponseBody 
	public Object arg5(Map map){
		Map initMap = (Map)map.get("init");
		return initMap.get("key1") + " from arg5";
	}
	
	/**
	 * @ModelAttribute 用在方法入参上, 将key对应的value绑定到所限定的参数上. 
	 * value的来源：
	 * 1） @SessionAttributes中有没有对应的key，有的话则用该key对应的value
	 * 2） @ModelAttribute所标注的方法有没有对应的key
	 * 3） 创建一个需要绑定的bean对象，将request中按名称对应的方式把value绑定到bean中
	 */
	@ModelAttribute("sessionKey1")
	public String sessionAttributes(){
		return "sessionValue1";
	}
	
	@RequestMapping(value="arg61")
	@ResponseBody 
	public Object arg61(@ModelAttribute("sessionKey1") String sessionvalue){
		return sessionvalue + " from arg61";
	}

	@RequestMapping(value="arg62")
	@ResponseBody 
	public Object arg62(@ModelAttribute("init") Map map){
		return map.get("key1") + " from arg62";
	}
	
	@RequestMapping(value="arg63")
	@ResponseBody 
	public String arg63(@ModelAttribute("key2") String value2, @ModelAttribute("init") Map map){
		return "key1 = "+(String)map.get("key1")+", key2 = " + value2;
	}
}
