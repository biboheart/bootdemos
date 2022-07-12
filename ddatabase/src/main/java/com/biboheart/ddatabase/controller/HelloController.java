package com.biboheart.ddatabase.controller;

import com.biboheart.ddatabase.service.ExportService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class HelloController {
    private final ExportService exportService;

    public HelloController(ExportService exportService) {
        this.exportService = exportService;
    }

    // 导出.svc 病案内容
    @RequestMapping(value = "/dbf/export", method = {RequestMethod.GET, RequestMethod.POST})
    public void exportDbf(String jzids, HttpServletResponse response) throws Exception {
        exportService.exportDBF(jzids, response);
    }
}
