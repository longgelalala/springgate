package com.zypcy.springcloud.dynamicroute.respository;

import com.zypcy.springcloud.dynamicroute.entity.PagePermission;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface PagePermissionDefinitionMapper {

    PagePermission selectByPrimaryKey(Long id);

    List<PagePermission> selectByRouteId(String routeId);

    List<PagePermission> listAll();

    int deleteByPrimaryKey(Long id);

    int insertSelective(PagePermission pagePermission);

    int updateByPrimaryKeySelective(PagePermission pagePermission);

    PagePermission selectByRouteIdAndRole(@Param("routeId") String routeId,@Param("role") String role);


}
