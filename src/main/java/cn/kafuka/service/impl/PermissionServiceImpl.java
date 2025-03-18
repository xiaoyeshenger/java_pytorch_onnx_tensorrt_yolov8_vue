package cn.kafuka.service.impl;

import cn.kafuka.cache.DictCache;
import com.github.pagehelper.PageHelper;
import cn.kafuka.bo.dto.PageReqDto;
import cn.kafuka.bo.po.Permission;
import cn.kafuka.bo.po.Role;
import cn.kafuka.bo.po.RolePermission;
import cn.kafuka.bo.vo.PageVo;
import cn.kafuka.mapper.*;
import cn.kafuka.redis.RedisService;
import cn.kafuka.util.ObjUtil;
import cn.kafuka.util.VoPoConverterUtil;
import cn.kafuka.bo.dto.PermissionPageReqDto;
import cn.kafuka.bo.dto.PermissionReqDto;
import cn.kafuka.bo.vo.MetaVo;
import cn.kafuka.bo.vo.PermsVo;
import cn.kafuka.service.PermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

/*
 * @Author 张勇
 * @Description //PermissionService接口实现类
 * @Date 2021/05/24 11:18
 * @Param
 * @return
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {


    private final DictCache dictCache;

    private final PermissionMapper permissionMapper;

    private final RolePermissionMapper rolePermissionMapper;

    private final RoleMapper roleMapper;


    @Override
    @Transactional
    public Map<String, Object> addPermission(PermissionReqDto permissionReqDto) {

        //1.参数校验
        //字典是否存在
        //(目录和菜单可以不提交perms和url，但是按钮必须提交)
        Long permsType = permissionReqDto.getPermsType();
        dictCache.getDataDictName(permsType);

        //2.设置参数
        Permission permission = VoPoConverterUtil.copyProperties(permissionReqDto, Permission.class);
        permission.setCreateTime(System.currentTimeMillis());

        //4.父级name
        Long parentId = permissionReqDto.getParentId();
        if(parentId != 0){
            Permission parentPerms = permissionMapper.selectByPrimaryKey(parentId);
            if(ObjUtil.isEmpty(parentPerms)){
                throw new IllegalArgumentException("id为:"+parentId+"的父级权限(菜单)不存在");
            }
        }

        if(permsType != 16){
            String routerName = permissionReqDto.getRouterName();
            if(ObjUtil.isEmpty(routerName)){
                throw new IllegalArgumentException("路由名称不能为空");
            }

            String component = permissionReqDto.getComponent();
            if(ObjUtil.isEmpty(component)){
                throw new IllegalArgumentException("组件路径不能为空");
            }

            String path = permissionReqDto.getPath();
            if(ObjUtil.isEmpty(path)){
                throw new IllegalArgumentException("调转路径不能为空");
            }
        }

        //6.保存
        permissionMapper.insert(permission);

        //7.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","添加权限信息成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> deletePermissionById(Long id){
        //1.权限是否存在
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        if(permission == null){
            throw new IllegalArgumentException("id为:"+id+"的权限信息不存在");
        }

        //2.查询到该权限所有的子集,并存入childList
        //--1.查询所有的权限
        List<Permission> childList = new ArrayList<>();
        childList = getAllChildPermList(permission,childList);
        //--2.将该权限本身也放入子集列表
        childList.add(permission);


        //3.删除权限和角色之间的关系  以及 权限
        childList.stream().forEach(p -> {
            //--1.删除权限和角色之间的关系
            rolePermissionMapper.deleteByExample()
                    .where(RolePermissionDynamicSqlSupport.permissionId, isEqualTo(p.getId()))
                    .build()
                    .execute();
            //--1.删除权限
            permissionMapper.deleteByExample()
                    .where(PermissionDynamicSqlSupport.id, isEqualTo(p.getId()))
                    .build()
                    .execute();
        });

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除权限成功");
        return resultMap;
    }

    //(*--1).通过父级列表数据，递归查询子集数据并加入到列表(直到没有子级),childList为传入的封装所有子集数据的列表
    public List<Permission> getAllChildPermList(Permission parentPerm,List<Permission> allChildList){

        //1.找到父级下的子集列表
        List<Permission> childPermissionList = getChildPermList(parentPerm);
        allChildList.addAll(childPermissionList);

        //2.子集列表不为空，递归查询子集
        for (int i = 0; i <childPermissionList.size() ; i++) {
            Permission curPermission = childPermissionList.get(i);
            List<Permission> childPermList = getChildPermList(curPermission);
            if(!ObjUtil.isEmpty(childPermList)){
                getAllChildPermList(curPermission, allChildList);

            }
        }
        return allChildList;
    }

    //(*--2).通过父级权限查询子集权限列表
    List<Permission> getChildPermList(Permission parentPerm){
        List<Permission> permissionList = permissionMapper.selectByExample()
                .where(PermissionDynamicSqlSupport.parentId, isEqualTo(parentPerm.getId()))
                .build()
                .execute();
        return permissionList;
    }


    @Override
    @Transactional
    public Map<String, Object> updatePermission(PermissionReqDto permissionReqDto) {

        //1.判断permissionReqDto是否存在
        Permission permission = permissionMapper.selectByPrimaryKey(permissionReqDto.getId());
        if(ObjUtil.isEmpty(permission)){
            throw new IllegalArgumentException("id为:"+permissionReqDto.getId()+"的权限不存在");
        }

        //2.判断权限类型(目录和菜单可以不提交permis和url，但是按钮必须提交)
        Long permsType = permissionReqDto.getPermsType();
        dictCache.getDataDictName(permsType);

        if("Button".equals(permsType)){
            if(ObjUtil.isEmpty(permissionReqDto.getPerms())){
                throw new IllegalArgumentException("权限标识不能为空");
            }
            if(ObjUtil.isEmpty(permissionReqDto.getComponent())){
                throw new IllegalArgumentException("访问路径不能为空");
            }
        }


        //3.更新Permission
        VoPoConverterUtil.beanConverter(permissionReqDto, permission);

        //4.父级
        Long parentId = permissionReqDto.getParentId();
        if(parentId != 0){
            Permission parentPerms = permissionMapper.selectByPrimaryKey(parentId);
            if(ObjUtil.isEmpty(parentPerms)){
                throw new IllegalArgumentException("id为:"+parentId+"的父级权限(菜单)不存在");
            }
        }

        //5.保存
        permissionMapper.updateByPrimaryKey(permission);

        //8.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","更新权限信息成功");
        return resultMap;
    }

    @Override
    public Map<String, Object> getPermissionById(Long id){
        Permission e = permissionMapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("id为:"+id+"的权限信息不存在");
        }

        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
        attr.put("name", e.getName());
        attr.put("routerName", e.getRouterName());
        attr.put("component", e.getComponent());
        attr.put("path", e.getPath());
        attr.put("perms", e.getPerms());
        attr.put("icon", e.getIcon());
        attr.put("parentId", e.getParentId());
        attr.put("parentName", e.getParentName());
        attr.put("orderNum", e.getOrderNum());
        attr.put("permsType", e.getPermsType());
        attr.put("linkType", e.getLinkType());
        attr.put("useType", e.getUseType());
        attr.put("status", e.getStatus());
        attr.put("createTime", e.getCreateTime());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getPermissionListPageVo(PermissionPageReqDto permissionPageReqDto){

        //1.设置分页条件
        PageHelper.startPage(permissionPageReqDto.getPageNum(), permissionPageReqDto.getPageSize());

        //2.查询语句构建
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Permission>>>.QueryExpressionWhereBuilder builder = permissionMapper.selectByExample().where();

        //3.根据查询条件封装查询命令
        //(1).关键字查询
        String name = permissionPageReqDto.getName();
        if(!ObjUtil.isEmpty(name)){
            builder.and(PermissionDynamicSqlSupport.name, isLike("%"+name+"%"));
        }

        Long useType = permissionPageReqDto.getUseType();
        if(!ObjUtil.isEmpty(useType)){
            builder.and(PermissionDynamicSqlSupport.useType, isEqualTo(useType));
        }

        Byte status = permissionPageReqDto.getStatus();
        if(!ObjUtil.isEmpty(status)){
            builder.and(PermissionDynamicSqlSupport.status, isEqualTo(status));
        }

        Long startTime = permissionPageReqDto.getStartTime();
        Long endTime = permissionPageReqDto.getEndTime();
        if (startTime != null && endTime != null) {
            builder.and(PermissionDynamicSqlSupport.createTime, isGreaterThanOrEqualTo(startTime));
            builder.and(PermissionDynamicSqlSupport.createTime, isLessThanOrEqualTo(endTime));
        } else {
            if (startTime != null) {
                builder.and(PermissionDynamicSqlSupport.createTime, isGreaterThanOrEqualTo(startTime));
            }
            if (endTime != null) {
                builder.and(PermissionDynamicSqlSupport.createTime, isLessThanOrEqualTo(endTime));
            }
        }

        //(2).排序
        builder.orderBy(PermissionDynamicSqlSupport.orderNum);

        //(3).查询
        List<Permission> list = builder.build().execute();

        //4.构建pageVo
        PageVo<Permission> pageVo = new PageVo<>(list);

        //5.对象转为map分页输出
        return PageVo.of(
                pageVo,
                e -> {
                    Map<String, Object> attr = new HashMap<>();
                    attr.put("id", e.getId());
                    attr.put("name", e.getName());
                    attr.put("routerName", e.getRouterName());
                    attr.put("component", e.getComponent());
                    attr.put("path", e.getPath());
                    attr.put("perms", e.getPerms());
                    attr.put("icon", e.getIcon());
                    attr.put("parentId", e.getParentId());
                    attr.put("parentName", e.getParentName());
                    attr.put("orderNum", e.getOrderNum());
                    attr.put("permsType", e.getPermsType());
                    attr.put("linkType", e.getLinkType());
                    attr.put("useType", e.getUseType());
                    attr.put("status", e.getStatus());
                    attr.put("createTime", e.getCreateTime());
                    return attr;
                }
        );
    }


    @Override
    public PageVo<Map<String, Object>> getAppPermissionListPageVo(PageReqDto pageReqDto){

        //1.设置分页条件
        PageHelper.startPage(pageReqDto.getPageNum(), pageReqDto.getPageSize());

        //2.查询语句构建
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Permission>>>.QueryExpressionWhereBuilder builder = permissionMapper.selectByExample().where();

        //3.根据查询条件封装查询命令
        //(1).关键字查询
        String searchKey = pageReqDto.getSearchKey();
        if(!ObjUtil.isEmpty(searchKey)){
            builder.and(PermissionDynamicSqlSupport.name, isLike("%"+searchKey+"%"));
        }

        //(2).类型(只搜索APP的跳转的按钮)
        builder.and(PermissionDynamicSqlSupport.useType, isEqualTo(18L));
        builder.and(PermissionDynamicSqlSupport.permsType, isEqualTo(16L));
        builder.and(PermissionDynamicSqlSupport.status, isEqualTo((byte)1));

        //(3).排序
        builder.orderBy(PermissionDynamicSqlSupport.orderNum);

        //(4).查询
        List<Permission> list = builder.build().execute();

        //4.构建PageBean
        PageVo<Permission> pageVo = new PageVo<>(list);

        //5.对象转为map分页输出
        return PageVo.of(
                pageVo,
                e -> {
                    Map<String, Object> attr = new HashMap<>();
                    attr.put("id", e.getId());
                    attr.put("name", e.getName());
                    attr.put("perms", e.getPerms());
                    attr.put("url", e.getUrl());
                    attr.put("icon", e.getIcon());
                    attr.put("parentId", e.getParentId());
                    attr.put("parentName", e.getParentName());
                    attr.put("orderNum", e.getOrderNum());
                    attr.put("permsType", e.getPermsType());
                    attr.put("linkType", e.getLinkType());
                    attr.put("path", e.getPath());
                    attr.put("status", e.getStatus());
                    attr.put("createTime", e.getCreateTime());
                    return attr;
                }
        );
    }


    @Override
    public List<Long> getParentPermIdList(Long permId,List<Long> idList) {
        Permission permission = getPermById(permId);
        if(ObjUtil.isEmpty(permission)){
            throw new IllegalArgumentException("id为:"+permId+"的权限不存在");
        }

        Long parentId = permission.getParentId();
        if(parentId != 0){
            idList.add(parentId);
            getParentPermIdList(parentId,idList);
        }
        return idList;
    }

    Permission getPermById(Long id){
        Permission e = permissionMapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("id为:"+id+"的权限信息不存在");
        }
        return e;
    }

    @Override
    public Permission getTopPerm(Long permId) {

        Permission permission = getPermById(permId);
        if(ObjUtil.isEmpty(permission)){
            throw new IllegalArgumentException("id为:"+permId+"的权限不存在");
        }

        Long parentId = permission.getParentId();
        if(parentId != 0){
            permission = getTopPerm(parentId);
        }

        return permission;
    }


    @Override
    public List<PermsVo> getPermsVoListTreeData(Long useType) {
        //1.获取到vo列表
        List<PermsVo> permsVoList = getPermsVoList(useType);

        //2.生成树状数据
        List<PermsVo> treeVoList = generatePermsVoTreeData(permsVoList);

        //3.返回结果
        return treeVoList;
    }


    @Override
    public List<PermsVo> getPermsVoListTreeDataByRoleId(Long roleId) {
        //1.角色是否存在
        Role role = roleMapper.selectByPrimaryKey(roleId);
        if(role == null){
            throw new IllegalArgumentException("id为:"+roleId+"的角色信息不存在");
        }

        List<PermsVo> treeVoList;
        if("SuperAdmin".equals(role.getRoleKey())){
            //1.超级管理员拥有所有权限
            List<PermsVo> allList = getPermsVoList(role.getUseType());
            treeVoList = generatePermsVoTreeData(allList);
        }else {
            //2.查询到该角色拥有的权限id列表
            List<RolePermission> rolePermissionList = rolePermissionMapper.selectByExample()
                    .where(RolePermissionDynamicSqlSupport.roleId, isEqualTo(roleId))
                    .build()
                    .execute();
            if(ObjUtil.isEmpty(rolePermissionList)){
                throw new IllegalArgumentException("该角色还未赋予任何权限");
            }
            List<Long> idList = rolePermissionList.stream().map(RolePermission::getPermissionId).collect(Collectors.toList());

            //3.处理该角色已选中的权限(selected设置为ture)
            //(1).所有的权限列表
            List<PermsVo> allList = getPermsVoList(role.getUseType());
            //(2).已选中的id和所有列表做比对,相同则代表该id被选中(该用户拥有此权限)
            List<PermsVo> permsVoList = processPermsVoSelected(idList, allList);
            //(3).生成树状数据
            treeVoList = generatePermsVoTreeData(permsVoList);
        }

        return treeVoList;
    }

    //4.获取所有的RouterVoVoList
    public List<PermsVo> getPermsVoList(Long useType) {
        //1.查询出所有的权限列表
        List<Permission> permissionList = permissionMapper.selectByExample()
                .where(PermissionDynamicSqlSupport.useType,isEqualTo(useType))
                .and(PermissionDynamicSqlSupport.status,isEqualTo((byte)1))
                .orderBy(PermissionDynamicSqlSupport.orderNum)
                .build()
                .execute();

        //2.列表转为permsVoList
        List<PermsVo> permsVoList = permissionList
                .stream()
                .map(p -> {
                    PermsVo permsVo = VoPoConverterUtil.copyProperties(p, PermsVo.class);
                    permsVo.setHidden(false)
                            .setAlwaysShow(true)
                            .setName(p.getRouterName())
                            .setPermsType(p.getPermsType())
                            .setRedirect("noRedirect")
                            .setMeta(new MetaVo(p.getName(), p.getIcon(), true, p.getPath()));
                    return permsVo;
                }).collect(Collectors.toList());
        return permsVoList;
    }

    //(1-1).构建PermsVo树状数据
    List<PermsVo> generatePermsVoTreeData(List<PermsVo> routerVoList){

        //--1.从源数据(permsVoList)中，获取数据字典所有根节点(父code为0),然后依次往下查询子节点
        List<PermsVo> rootDataPermsVoList = new ArrayList<>();
        for(PermsVo routerVo : routerVoList) {
            if(routerVo.getParentId().equals(0L)) {
                rootDataPermsVoList.add(routerVo);
            }
        }

        //--2.给每个根节点建立树形结构(相当于构建所有的数据字典为树形结构)
        List<PermsVo> treeRouterVoList = new ArrayList<>();
        for(PermsVo permsVo : rootDataPermsVoList){
            permsVo = buildPermsVoChildTree(routerVoList,permsVo);
            treeRouterVoList.add(permsVo);
        }

        return treeRouterVoList;
    }


    //(1-2).通过父级数据，递归建立RouterVo子树形结构(直到没有子级)
    public PermsVo buildPermsVoChildTree(List<PermsVo> permsVoList, PermsVo parentPermsVo){
        List<PermsVo> childVoList = new ArrayList<>();
        for(PermsVo permsVo : permsVoList) {
            Long parentId = permsVo.getParentId();
            Long id = parentPermsVo.getId();
            if(parentId.equals(id)) {
                childVoList.add(buildPermsVoChildTree(permsVoList,permsVo));
            }
        }
        parentPermsVo.setChildren(childVoList);
        return parentPermsVo;
    }

    //(1-3).处理某个角色已选中(拥有)的权限(PermsVo),未选中的排除到列表之外
    public List<PermsVo> processPermsVoSelected(List<Long> idList, List<PermsVo> permsVoList){

        //--1.该角色只拥有的权限
        List<PermsVo> rolePermsVoList = new ArrayList<>();

        //--2.遍历所有的权限，找出ID相同的，并加入到rolePermsVoList
        for (int i = 0; i < idList.size(); i++) {
            Long currentId = idList.get(i);
            for (int j = 0; j < permsVoList.size() ; j++) {
                PermsVo permsVo = permsVoList.get(j);
                if(currentId.equals(permsVo.getId())){
                    rolePermsVoList.add(permsVo);
                }
            }
        }
        return rolePermsVoList;
    }
}