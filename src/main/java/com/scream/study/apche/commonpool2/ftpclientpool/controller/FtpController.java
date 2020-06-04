package com.scream.study.apche.commonpool2.ftpclientpool.controller;

import com.scream.study.apche.commonpool2.ftpclientpool.util.FtpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping("/ftp")
@Api(tags = "ftp")
public class FtpController {
//	https://blog.csdn.net/qq_40808344/article/details/86763575
//	https://blog.csdn.net/AaronSimon/article/details/94590093
	@Autowired
	private GenericObjectPool<FTPClient> ftpClientPool;




	@ApiOperation(value = "上传卷宗文件")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bmsah", value = "部门受案号", required = true, paramType = "path", defaultValue = "xxxxxx号"),
			@ApiImplicitParam(name = "location", value = "卷宗文件路径", required = true, paramType = "body")
	})
	@PostMapping(value="/uploadData/{bmsah}")
	public void upload(@PathVariable String bmsah, @RequestBody String location) {
		try {
			FTPClient client = ftpClientPool.borrowObject();
			File file = new File(location);
			System.out.println(client);
			FtpUtil ftpUtil = new FtpUtil(client);
			ftpUtil.upload("xxxx.log",file);
			ftpClientPool.returnObject(client);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
