package com.scream.study.apche.commonpool2.ftpclientpool;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FtpPoolConfiguraion {
	@Autowired
	private FtpClientProperties ftpClientProperties;
	@Bean
	public GenericObjectPool<FTPClient> ftpClientPool() {
		GenericObjectPoolConfig conf = new GenericObjectPoolConfig();
		conf.setMaxTotal(20);
		conf.setMaxIdle(10);
		GenericObjectPool<FTPClient> pool = new GenericObjectPool<FTPClient>(new FtpClientFactory(ftpClientProperties), conf);
		return pool;
	}
}
