package com.scream.study.redis;

import com.scream.study.redis.service.RedisService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/redis")
public class RedisController {
    @Resource
    private RedisService redisService;

    @RequestMapping("/set/{key}/{value}")
    public void set(@PathVariable String key, @PathVariable String value) {
        redisService.set(key, value);
    }

    @RequestMapping("/get/{key}")
    public String get(@PathVariable String key) {
        return (String) redisService.get(key);
    }

    @RequestMapping("/hset/{key}/{hkey}/{value}")
    public void hset(@PathVariable String key, @PathVariable String hkey, @PathVariable String value) {
        redisService.hSet(key, hkey, value);
    }

    @RequestMapping("/hget/{key}/{hkey}")
    public String hget(@PathVariable String key, @PathVariable String hkey) {
        return (String) redisService.hGet(key, hkey);
    }
}

