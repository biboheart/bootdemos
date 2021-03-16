package com.biboheart.dhttpclient.controller;

import com.biboheart.brick.model.BhResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@RestController
public class DemoController {
    private final RestTemplate restTemplate;

    @Autowired
    public DemoController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public void list(HttpServletResponse response) {
        ResponseEntity<Resource> entity = restTemplate.getForEntity("http://192.168.2.127/platformdocking/platformapi/docking/inspection/list?number=123&access_token=12045320-93c7-4083-8d6a-6214a9580c98", Resource.class);
        Resource body = entity.getBody();
        if (null != body) {
            try {
                InputStream is = body.getInputStream();
                ServletOutputStream os = response.getOutputStream();
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setCharacterEncoding("UTF-8");
                byte[] cache = new byte[1024];
                int nRead = 0;
                while ((nRead = is.read(cache)) != -1) {
                    os.write(cache, 0, nRead);
                    os.flush();
                }
                os.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/demo", method = {RequestMethod.GET})
    public BhResponseResult<?> test(HttpServletResponse response) {
        /*ResponseEntity<String> entity = restTemplate.getForEntity("http://120.26.195.9:3962/Report/Report/?PatientsID=45584", String.class);
        String body = entity.getBody();
        try {
            response.getWriter().write(body);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        ResponseEntity<Resource> entity = restTemplate.getForEntity("http://120.26.195.9:3962/Report/Report/?PatientsID=45584", Resource.class);
        Resource body = entity.getBody();
        if (null != body) {
            try {
                InputStream is = body.getInputStream();
                ServletOutputStream os = response.getOutputStream();
                byte[] cache = new byte[1024];
                int nRead = 0;
                while ((nRead = is.read(cache)) != -1) {
                    os.write(cache, 0, nRead);
                    os.flush();
                }
                os.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new BhResponseResult<>(0, "success", true);
    }
}
