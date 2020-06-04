package com.scream.study.http.resttemplate.template;

import com.scream.study.http.resttemplate.bean.Notice;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class GetRestTemplate {
	private static RestTemplate restTemplate = new RestTemplate();
	private static final String MSG = "天王盖地虎";
	private static final String REQUEST_URL = "http://localhost/study/resttemplate/getRequest/";


//	Get请求

//	getForObject
//	public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables){}
//	public <T> T getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables)
//	public <T> T getForObject(URI url, Class<T> responseType)

	//	getForEntity
//	public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object... uriVariables){}
//	public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Map<String, ?> uriVariables){}
//	public <T> ResponseEntity<T> getForEntity(URI url, Class<T> responseType){}
	@Before
	public void before() {
		// RestTemplate中对字符串使用的默认编码是ISO_8859_1 需要设置为UTF-8
		restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
	}

	/**
	 * 不带参的getForObject请求
	 * HTTP GET http://localhost/study/resttemplate/getRequest/variable1/variable2
	 */
	@Test
	public void getForObject1() {
		Notice notice = restTemplate.getForObject(REQUEST_URL + "variable1" + "/variable2", Notice.class);
		System.out.println(notice);
	}

	/**
	 * 带参数的getForObject请求-占位符
	 * HTTP GET http://localhost/study/resttemplate/getRequest/variable1/variable2
	 * HTTP GET http://localhost/study/resttemplate/getRequest/xxx/vvv
	 */
	@Test
	public void getForObject2() {
		Notice notice = null;
		notice = restTemplate.getForObject(REQUEST_URL + "{1}/{2}", Notice.class, "variable1", "variable2");//按位替换
		Map<String, String> map = new HashMap();
		map.put("variable1", "xxx");
		map.put("variable2", "vvv");
		notice = restTemplate.getForObject(REQUEST_URL + "{variable1}/{variable2}", Notice.class, map);//按key替换
		System.out.println(notice);
	}

	/**
	 * 不带参的getForEntity请求
	 * HTTP GET http://localhost/study/resttemplate/getRequest/variable1/variable2
	 */
	@Test
	public void getForEntity1() {
		ResponseEntity<Notice> entity = restTemplate.getForEntity(REQUEST_URL + "variable1" + "/variable2", Notice.class);
		System.out.println(entity.getStatusCode());
		System.out.println(entity.getBody());
		System.out.println(entity.getHeaders());
	}

	/**
	 * 带参数的getForEntity请求1-占位符
	 * HTTP GET http://localhost/study/resttemplate/getRequest/variable1/variable2
	 * HTTP GET http://localhost/study/resttemplate/getRequest/xxx/vvv
	 */
	@Test
	public void getForEntity2() {
		ResponseEntity<Notice> entity = null;
		entity = restTemplate.getForEntity(REQUEST_URL + "{1}/{2}", Notice.class, "variable1", "variable2");//按位替换
		Map<String, String> map = new HashMap();
		map.put("variable1", "xxx");
		map.put("variable2", "vvv");
		entity = restTemplate.getForEntity(REQUEST_URL + "{variable1}/{variable2}", Notice.class, map);//按key替换
		System.out.println(entity.getStatusCode());
		System.out.println(entity.getBody());
		System.out.println(entity.getHeaders());
	}

	/**
	 * create by: scream
	 * create time: 2020/2/14 1:30
	 * description: HTTP GET http://localhost/study/resttemplate/getRequest/xxxxxxxx/vvvvvvvv?requestmsg=%E5%A4%A9%E7%8E%8B%E7%9B%96%E5%9C%B0%E8%99%8E
	 * paramMap 处理@PathVariable相关参数
	 * UriComponentsBuilder 处理@RequestParam相关参数
	 */

	@Test
	public void exchange() {
		// header填充
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity(map, headers);

		// 拼接@PathVariable
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("xxx", "xxxxxxxx");
		paramMap.put("vvv", "vvvvvvvv");

		// 拼接@RequestParam
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(REQUEST_URL + "{xxx}" + "/{vvv}");
		builder.queryParam("requestmsg", MSG);
		String reallyUrl = builder.build().toString();

		// 发送带@PathVariable相关参数的请求
		ResponseEntity responseEntity = restTemplate.exchange(reallyUrl, HttpMethod.GET, entity, String.class, paramMap);
		// 发送带@RequestParam相关参数的请求
		// restTemplate.exchange(reallyUrl, HttpMethod.GET, entity, String.class);
		System.out.println(responseEntity.getBody());
	}

}
