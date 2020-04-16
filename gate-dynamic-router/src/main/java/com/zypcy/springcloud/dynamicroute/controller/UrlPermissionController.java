package com.zypcy.springcloud.dynamicroute.controller;


import com.zypcy.springcloud.dynamicroute.entity.GatewayRoutes;
import com.zypcy.springcloud.dynamicroute.dto.SysRole;
import com.zypcy.springcloud.dynamicroute.entity.PagePermission;
import com.zypcy.springcloud.dynamicroute.service.impl.PagePermissionServiceImpl;
import com.zypcy.springcloud.dynamicroute.service.inf.IRoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/url")
public class UrlPermissionController {
    @Autowired
    private IRoutesService routesService;
    @Autowired
    private PagePermissionServiceImpl pagePermissionService;


    @RequestMapping("getAllPermissions")
    @ResponseBody
    public List<PagePermission> getAll() {

        return pagePermissionService.getPagePermission();
    }


    @RequestMapping(value = "/getAllRole", method = RequestMethod.GET)
    public String getAllRole(ModelMap map, String routeId) {

        //取出所有角色
        String url = "http://localhost:8081/role/getAll";
        RestTemplate restTemplate = new RestTemplate();
        List<SysRole> sysRoleList = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<SysRole>>() {
        }).getBody();

        map.addAttribute("sysrole", sysRoleList);
        map.addAttribute("routeId", routeId);

        return "addPagePermission";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(ModelMap map, String routeId, String role) {

        PagePermission pagePermission = pagePermissionService.getPagePermissionByRouteIdAndRole(routeId, role);
        map.addAttribute("routeId", routeId);
        map.addAttribute("role", role);
        if (pagePermission != null)
            map.addAttribute("pagePermission", pagePermission);
        else
            map.addAttribute("pagePermission", new PagePermission());
        return "addPagePermission01";
    }

    //添加url权限信息
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String edit(@RequestBody PagePermission pagePermission) {
        return pagePermissionService.update(pagePermission) > 0 ? "success" : "fail";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(@RequestBody PagePermission pagePermission) {
        return pagePermissionService.add(pagePermission) > 0 ? "success" : "fail";
    }

    @RequestMapping(value = "/getCurPagePermissons", method = RequestMethod.GET)
    @ResponseBody
    public String getCurrentPagePermission(String routeId, String access_token) {

        //TODO 从token解析出来什么角色并返回对应的权限
        String role = getRole(access_token);
        System.out.println("role--------"+role);
        PagePermission pagePermission = pagePermissionService.getPagePermissionByRouteIdAndRole(routeId, role);
        if (pagePermission != null && pagePermission.getPermissionValue() != null)
            return pagePermission.getPermissionValue();
        return "权限尚未配置";
    }
//
//    {
//        alg: "HS256",
//                typ: "JWT"
//    }.
//    {
//        user_name: "admin",
//                scope: [
//        "read",
//                "write"
// ],
//        designer: "zml",
//                exp: 1584338383,
//            authorities: [
//        "ROLE_ADMIN"
// ],
//        jti: "b5a7466a-b15d-489a-941c-f07ec3c96572",
//                client_id: "client"
//    }.

    private String getRole(String access_token) {

        String[] tokens = access_token.split("\\.");
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = new byte[0];
        try {
            bytes = decoder.decodeBuffer(tokens[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String role = new String(bytes);
        role = role.substring(role.indexOf("ROLE_") + 5);
        role = role.substring(0, role.indexOf("\"]"));

        return role;
    }

}
