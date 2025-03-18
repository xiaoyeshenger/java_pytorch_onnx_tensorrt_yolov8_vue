package cn.kafuka.service;

import cn.kafuka.bo.dto.PageReqDto;
import cn.kafuka.bo.po.Permission;
import cn.kafuka.bo.vo.PageVo;
import cn.kafuka.bo.dto.PermissionPageReqDto;
import cn.kafuka.bo.dto.PermissionReqDto;
import cn.kafuka.bo.vo.PermsVo;

import java.util.*;

public interface PermissionService {

    Map<String, Object> addPermission(PermissionReqDto permissionReqDto);

    Map<String, Object> deletePermissionById(Long id);


    List<Permission> getAllChildPermList(Permission parentPerm, List<Permission> allChildList);

    Map<String, Object> updatePermission(PermissionReqDto permissionReqDto);

    Map<String, Object> getPermissionById(Long id);

    PageVo<Map<String, Object>> getPermissionListPageVo(PermissionPageReqDto permissionPageReqDto);


    PageVo<Map<String, Object>> getAppPermissionListPageVo(PageReqDto pageReqDto);


    List<Long> getParentPermIdList(Long permId, List<Long> idList);

    Permission getTopPerm(Long permId);


    List<PermsVo> getPermsVoListTreeData(Long useType);


    List<PermsVo> getPermsVoListTreeDataByRoleId(Long roleId);

    List<PermsVo> getPermsVoList(Long useType);


    PermsVo buildPermsVoChildTree(List<PermsVo> permsVoList, PermsVo parentPermsVo);


    List<PermsVo> processPermsVoSelected(List<Long> idList, List<PermsVo> permsVoList);

}