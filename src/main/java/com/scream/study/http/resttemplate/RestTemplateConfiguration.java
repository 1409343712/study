package com.scream.study.http.resttemplate;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

	@Bean
	public HttpClient httpClient() {
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.getSocketFactory())
				.register("https", SSLConnectionSocketFactory.getSocketFactory())
				.build();
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
		// 设置最大连接数。高于这个值时，新连接请求，需要阻塞，排队等待
		connectionManager.setMaxTotal(200);
		// 路由是对MaxTotal的细分。
		// 每个路由实际最大连接数默认值是由DefaultMaxPerRoute控制。
		// MaxPerRoute设置的过小，无法支持大并发：ConnectionPoolTimeoutException:Timeout waiting for connection from pool
		// 每个路由的最大连接
		connectionManager.setDefaultMaxPerRoute(200);
		//在从连接池获取连接时，连接不活跃多长时间后需要进行一次验证，默认为2s
		connectionManager.setValidateAfterInactivity(5 * 1000);
		RequestConfig requestConfig = RequestConfig.custom()
				// 客户端和服务器建立连接后，客户端等待服务器返回Response的超时时间
				// 超出后会抛出SocketTimeOutException
				.setSocketTimeout(5 * 1000) // 默认一直等待
				// 客户端和服务器建立连接的超时时间
				// 超时会会抛出ConnectionTimeoutException
				.setConnectTimeout(5 * 1000)// 默认一直等待
				// 从连接池获取连接的超时时间
				// 超时后会抛出ConnectionPoolTimeoutException
				.setConnectionRequestTimeout(5 * 1000) // 默认一直等待
				.build();

		return HttpClientBuilder.create()
				.setDefaultRequestConfig(requestConfig)
				.setConnectionManager(connectionManager)
                // .setKeepAliveStrategy(xxxxx) 可以自定义keepalived策略
				.build();

	}


	@Bean
	public ClientHttpRequestFactory httpRequestFactory() {
		return new HttpComponentsClientHttpRequestFactory(httpClient());
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate(httpRequestFactory());
	}
}
