package com.biboheart.dhelloworld.api;

import com.biboheart.dhelloworld.service.ExportService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class HelloController {
    @Value("${custom.name:default}")
    private String customName;
    private final ExportService exportService;

    public HelloController(ExportService exportService) {
        this.exportService = exportService;
    }

    @RequestMapping(value = "/hello")
    public String hello(String name) {
        System.out.println("配置文件自定义配置的值是: " + customName);
        return "hello " + name + " with " + customName;
    }

    // 导出.svc 病案内容
    @RequestMapping(value = "/dbf/export", method = {RequestMethod.GET, RequestMethod.POST})
    public void exportDbf(String jzids, HttpServletResponse response) throws Exception {
        exportService.exportDBF(jzids, response);
    }
}
