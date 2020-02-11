package com.zypcy.springcloud.dynamicroute.controller;


import com.zypcy.springcloud.dynamicroute.dto.SysRole;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping(value = "/role")
public class SysRoleController {
    @RequestMapping(value = "/getAll")
    public List testVue() {
        String url = "http://localhost:8081/role/getAll";
        RestTemplate restTemplate = new RestTemplate();
        List<SysRole> responseBean = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<SysRole>>() {
        }).getBody();
        return responseBean;
    }



}
