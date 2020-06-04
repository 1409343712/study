package com.scream.study.apche.commonpool2.ftpclientpool;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "system.ftpinfo")
@EnableConfigurationProperties
public class FtpClientProperties {
	// ftp地址
	private String host;
	// 端口号
	private Integer port;
	// 登录用户
	private String username;
	// 登录密码
	private String password;
	// 被动模式
	private boolean passiveMode = false;
	// 编码
	private String encoding = "UTF-8";
	// 连接超时时间(秒)
	private Integer connectTimeout;
	// 缓冲大小
	private Integer bufferSize = 1024;
	// FTP根目录
	private String ftpBasePath;
}
