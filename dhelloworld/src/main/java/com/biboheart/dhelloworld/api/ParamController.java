package com.biboheart.dhelloworld.api;

import com.biboheart.dhelloworld.model.Person;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParamController {

    @RequestMapping(value = "/demo/param/path/{sn}")
    public String pathParam(@PathVariable String sn) {
        return "接收到参数:" + sn;
    }

    @RequestMapping(value = "/demo/param/req")
    public String reqParam(String name, Integer age) {
        return "接收到参数:{name:" + name + ";age:" + age + "}";
    }

    @RequestMapping(value = "/demo/param/model")
    public String modelParam(Person person) {
        return "接收到参数:" + person.toString();
    }

    @RequestMapping(value = "/demo/param/json")
    public String jsonParam(@RequestBody Person person) {
        String str = "test";
        int start = str.indexOf("<tns:record>") + "<tns:record>".length();
        int end = str.indexOf("</tns:record>");
        return "接收到参数:" + person.toString();
    }
}
