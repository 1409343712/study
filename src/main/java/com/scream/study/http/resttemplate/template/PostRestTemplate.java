package com.scream.study.http.resttemplate.template;

import com.alibaba.fastjson.JSON;
import com.scream.study.http.resttemplate.bean.Notice;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class PostRestTemplate {
	private static RestTemplate restTemplate = new RestTemplate();
	private static final String MSG = "天王盖地虎";

	private static final String SIMPLE_REQUEST_URL = "http://localhost/study/resttemplate/simplePostRequest";
	private static final String REQUEST_URL = "http://localhost/study/resttemplate/postRequest/";
	private static HttpEntity<String> entity = null;

//	post请求

//	postForObject
//	public <T> T postForObject(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {}
//	public <T> T postForObject(String url, @Nullable Object request, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {}
//	public <T> T postForObject(URI url, @Nullable Object request, Class<T> responseType) throws RestClientException {}

//	postForEntity
//	public <T> ResponseEntity<T> postForEntity(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException
//	public <T> ResponseEntity<T> postForEntity(String url, @Nullable Object request, Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException
//	public <T> ResponseEntity<T> postForEntity(URI url, @Nullable Object request, Class<T> responseType) throws RestClientException

	@Before
	public void before() {
		// RestTemplate中对字符串使用的默认编码是ISO_8859_1 需要设置为UTF-8
		restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		Notice notice = new Notice();
		notice.setRequestMsg(MSG);
		entity = new HttpEntity(JSON.toJSONString(notice), headers);

	}

	/**
	 * 不带参的postForObject请求
	 * HTTP POST http://localhost/study/resttemplate/simplePostRequest
	 */
	@Test
	public void postForObject1() {
		Notice notice = restTemplate.postForObject(SIMPLE_REQUEST_URL, entity, Notice.class);
		System.out.println(notice);
	}

	/**
	 * 带参数的postForObject请求-占位符
	 * HTTP POST http://localhost/study/resttemplate/postRequest/xxx/vvv
	 * TTP POST http://localhost/study/resttemplate/postRequest/xxxxx/vvvvv
	 */
	@Test
	public void postForObject2() {
		Notice notice = null;
		notice = restTemplate.postForObject(REQUEST_URL + "{1}" + "/{2}", entity, Notice.class, "xxx", "vvv");
		System.out.println(notice);
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("xxx", "xxxxx");
		paramMap.put("vvv", "vvvvv");
		notice = restTemplate.postForObject(REQUEST_URL + "{xxx}" + "/{vvv}", entity, Notice.class, paramMap);
		System.out.println(notice);
	}

	/**
	 * 不带参的postForEntity请求
	 * HTTP POST http://localhost/study/resttemplate/simplePostRequest
	 */
	@Test
	public void postForEntity1() {
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(SIMPLE_REQUEST_URL, entity, String.class);
		System.out.println(responseEntity.getBody());
	}

	/**
	 * 带参数的postForObject请求-占位符
	 * HTTP POST http://localhost/study/resttemplate/postRequest/xxx/vvv
	 * HTTP POST http://localhost/study/resttemplate/postRequest/xxxxx/vvvvv
	 */
	@Test
	public void postForEntity2() {
		ResponseEntity<String> responseEntity = null;
		responseEntity = restTemplate.postForEntity(REQUEST_URL + "{1}" + "/{2}", entity, String.class, "xxx", "vvv");
		System.out.println(responseEntity.getBody());
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("xxx", "xxxxx");
		paramMap.put("vvv", "vvvvv");
		responseEntity = restTemplate.postForEntity(REQUEST_URL + "{xxx}" + "/{vvv}", entity, String.class, paramMap);
		System.out.println(responseEntity.getBody());
	}

	/**
	 * exchange方式发送post请求
	 * HTTP POST http://localhost/study/resttemplate/postRequest/xxxxxxxx/vvvvvvvv
	 */
	@Test
	public void ss() {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("xxx", "xxxxxxxx");
		paramMap.put("vvv", "vvvvvvvv");
		ResponseEntity responseEntity = restTemplate.exchange(REQUEST_URL + "{xxx}" + "/{vvv}", HttpMethod.POST, entity, String.class, paramMap);
		System.out.println(responseEntity.getBody());
	}

}
