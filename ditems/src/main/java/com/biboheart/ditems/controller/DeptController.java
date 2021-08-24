package com.biboheart.ditems.controller;

import com.biboheart.brick.model.BhResponseResult;
import com.biboheart.ditems.entity.Dept;
import com.biboheart.ditems.service.DeptService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {
    private final DeptService deptService;

    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }

    @RequestMapping(value = "/dept/save", method = {RequestMethod.POST})
    public BhResponseResult<?> save(Dept dept) {
        return new BhResponseResult<>(0, "success", deptService.save(dept));
    }

    @RequestMapping(value = "/dept/load", method = {RequestMethod.POST, RequestMethod.GET})
    public BhResponseResult<?> load(String sn) {
        return new BhResponseResult<>(0, "success", deptService.load(sn));
    }

    @RequestMapping(value = "/dept/list", method = {RequestMethod.POST, RequestMethod.GET})
    public BhResponseResult<?> list() {
        return new BhResponseResult<>(0, "success", deptService.list(null, null, null));
    }
}
