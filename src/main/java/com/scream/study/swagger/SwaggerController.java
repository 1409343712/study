package com.scream.study.swagger;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

@Api(tags = "Swaggerp配置演示")
@RestController
public class SwaggerController {

    @ApiOperation(value = "测试 - GET请求参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "name", value = "名称", required = false, paramType = "query")
    })
    @GetMapping(value = "/testGet")
    public ResponseEntity<String> testGet(@RequestParam Long id, @RequestParam String name) {
        return ResponseEntity.ok("编号: " + id + "名称: " + name);
    }
    @ApiOperation(value = "测试 - Post请求参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", required = true, paramType = "path"),
            @ApiImplicitParam(name = "name", value = "名称", required = false, paramType = "path")
    })
    @PostMapping(value = "/testPost/{id}/{name}")
    public ResponseEntity<String> testPost(@PathVariable Long id, @PathVariable String name) {
        return ResponseEntity.ok("编号: " + id + "名称: " + name);
    }
    @ApiOperation(value = "测试 - Post请求参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "queryBody", value = "queryBody", required = true)
    })
    @PostMapping(value = "/testPost")
    public ResponseEntity<String> testPost(@RequestBody QueryBody queryBody) {
        return ResponseEntity.ok("编号: " + queryBody.getId()+ "名称: " + queryBody.getName());
    }
}
