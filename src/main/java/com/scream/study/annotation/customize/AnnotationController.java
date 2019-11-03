package com.scream.study.annotation.customize;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("annotation")
public class AnnotationController {

    @RequestMapping("test")
    @PersonalAnnotation(value = "test", error = "哎呀我去，居然出现异常了", OperationType = OperationType.UNKNOWN)
    public Map<String, String> test() {
        Map<String, String> result = new HashMap<String, String>();
        result.put("x", "xxxxx");
        return result;
    }
}
