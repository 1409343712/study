package com.scream.study.http.resttemplate.controller;

import com.alibaba.fastjson.JSON;
import com.scream.study.http.resttemplate.bean.Notice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resttemplate")
public class RestTemplateController {

	@GetMapping("/getRequest/{variable1}/{variable2}")
	public String getRequest(@PathVariable String variable1, @PathVariable String variable2, @RequestParam(name = "requestmsg",required = false) String requestmsg) {
		System.out.println("variable1: " + variable1);
		System.out.println("variable2: " + variable2);
		Notice notice = new Notice();
		notice.setRequestMsg(requestmsg);
		notice.setResponseMsg("宝塔镇河妖");
		return JSON.toJSONString(notice);
	}

	@PostMapping("/simplePostRequest")
	public String simplePostRequest(@RequestBody Notice notice) {
		notice.setResponseMsg("宝塔镇河妖");
		return JSON.toJSONString(notice);
	}

	@PostMapping("/postRequest/{variable1}/{variable2}")
	public String postRequest(@PathVariable(name = "variable1", required = false) String variable1,
	                          @PathVariable(name = "variable2", required = false) String variable2,
	                          @RequestBody Notice notice) {
		System.out.println("variable1: " + variable1);
		System.out.println("variable2: " + variable2);
		notice.setResponseMsg("宝塔镇河妖");
		return JSON.toJSONString(notice);
	}
}
