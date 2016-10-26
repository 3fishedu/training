package com.threefish.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("rm")
public class RequestMappingController {

	@RequestMapping("1")
	@ResponseBody
	public String Rm1(){
		return "rm1";
	}
	
	@RequestMapping({"2","21","22"})
	@ResponseBody
	public String Rm2(){
		return "rm2";
	}
	
	@RequestMapping(method=RequestMethod.POST,value="3")
	@ResponseBody
	public String Rm3(){
		return "rm3";
	}
	
	@RequestMapping(method=RequestMethod.GET,value="4",params="rm4=1")
	@ResponseBody
	public String Rm4(){
		return "rm4";
	}
			
	@RequestMapping(method=RequestMethod.GET,value="5",headers = "key5=value5")
	@ResponseBody
	public String Rm5(){
		return "rm5";
	}
	
	//Request Headers: Content-Type -> consumes
	//Request Headers: Accept -> produces
	@RequestMapping(value="6",consumes="text/html",produces="application/xml")
	@ResponseBody
	public String Rm6(){
		return "rm6";
	}
}
