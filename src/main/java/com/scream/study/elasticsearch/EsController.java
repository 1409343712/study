package com.scream.study.elasticsearch;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/es")
@Api(tags = "es功能验证")
public class EsController {
    @Autowired
    private UserDao userDao;

    @ApiOperation(value = "测试 - addUser")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "entity", value = "entity", required = true)
    })
    @PostMapping("/addUser")
    public UserEntity addUser(@RequestBody UserEntity entity) {
        return userDao.save(entity);
    }

    @ApiOperation(value = "测试 - findById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path")
    })
    @PostMapping("/findById/{id}")
    public Optional<UserEntity> findById(@PathVariable String id) {
        return userDao.findById(id);
    }

    @ApiOperation(value = "测试 - deleteById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path")
    })
    @PostMapping("/deleteById/{id}")
    public void deleteById(@PathVariable String id) {
        userDao.deleteById(id);
    }
}
