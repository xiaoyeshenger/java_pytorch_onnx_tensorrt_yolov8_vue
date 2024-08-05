package cn.kafuka.service.impl;

import cn.kafuka.util.ObjUtil;
import com.github.pagehelper.PageHelper;
import cn.kafuka.bo.po.Permission;
import cn.kafuka.bo.po.Role;
import cn.kafuka.bo.po.RolePermission;
import cn.kafuka.bo.vo.PageVo;
import cn.kafuka.mapper.*;
import cn.kafuka.util.*;
import cn.kafuka.bo.dto.RolePageReqDto;
import cn.kafuka.bo.dto.RoleReqDto;
import cn.kafuka.bo.vo.PermsVo;
import cn.kafuka.service.PermissionService;
import cn.kafuka.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

/*
 * @Author 张勇
 * @Description //RoleService接口实现类
 * @Date 2021/05/24 11:26
 * @Param
 * @return
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final PermissionService permissionService;

    private final RoleMapper roleMapper;

    private final RolePermissionMapper rolePermissionMapper;

    private final PermissionMapper permissionMapper;


    @Override
    @Transactional
    public Map<String, Object> addRole(String userInfo,RoleReqDto roleReqDto) {

        //1.校验参数
        //(1).名称是否存在
        String reqName = roleReqDto.getName();
        Role execute = roleMapper.selectByExampleOne()
                .where(RoleDynamicSqlSupport.name, isEqualTo(reqName))
                .build()
                .execute();
        if(!ObjUtil.isEmpty(execute)){
            throw new IllegalArgumentException("名称为:"+reqName+"的角色名称已存在");
        }


        //2.设置参数
        //(1)复制RoleReqDto中的请求参数到Role
        Role role = VoPoConverterUtil.copyProperties(roleReqDto, Role.class);

        //(2)设置其他属性
        Long curTime = new Date().getTime();
        Long roleId = new IdWorker().nextId();
        role.setCreateTime(curTime);
        role.setDelFlag((byte)0);
        role.setCreateTime(curTime);
        role.setId(roleId);

        //3.保存role
        roleMapper.insert(role);

        //4.保存role-permission中间表
        List<Long> permsList = roleReqDto.getPermsList();
        permsList.forEach(p ->{
            Permission permission = permissionMapper.selectByPrimaryKey(p);
            if(ObjUtil.isEmpty(permission)){
                throw new IllegalArgumentException("id为: "+ p +" 的权限不存在");
            }

            List<Long> idList = new ArrayList<>();
            idList = permissionService.getParentPermIdList(p,idList);

            //判断当前权限的父级权限是否存在(提交权限必须提交父级权限知道顶级权限)
            boolean subset = CollectionUtil.isSubset(permsList, idList);
            if(!subset){
                throw new IllegalArgumentException("id为: "+ p +" 的权限缺少父级(或顶级)权限id");
            }

            RolePermission rolePermission =RolePermission.builder()
                    .roleId(roleId)
                    .permissionId(p)
                    .build();
            rolePermissionMapper.insert(rolePermission);
        });

        //5.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","添加角色信息成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> deleteRoleById(String userInfo,Long id){
        Role role = roleMapper.selectByPrimaryKey(id);
        if(role == null){
            throw new IllegalArgumentException("id为:"+id+"的角色信息不存在");
        }
        //删除角色
        roleMapper.deleteByExample()
                    .where(RoleDynamicSqlSupport.id, isEqualTo(id))
                    .build()
                    .execute();

        //删除角色-权限关联关系
        rolePermissionMapper.deleteByExample()
                .where(RolePermissionDynamicSqlSupport.roleId, isEqualTo(id))
                .build()
                .execute();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除角色成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> updateRole(String userInfo,RoleReqDto roleReqDto) {

        //1.参数校验
        //(1).判断role是否存在
        Long id = roleReqDto.getId();
        Role role = roleMapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(role)){
            throw new IllegalArgumentException("id为:"+ id +"的角色不存在");
        }

        //2.是否修改了名称
        String name = role.getName();
        String reqName = roleReqDto.getName();
        if(!Objects.equals(name,reqName)){
            Role execute = roleMapper.selectByExampleOne()
                    .where(RoleDynamicSqlSupport.name, isEqualTo(reqName))
                    .build()
                    .execute();
            if(!ObjUtil.isEmpty(execute)){
                throw new IllegalArgumentException("名称为:"+reqName+"的角色已存在");
            }
        }

        //3.更新Role
        //(1)复制RoleDto中的请求参数到Role
        VoPoConverterUtil.beanConverterNotNull(roleReqDto, role);

        //4.保存
        roleMapper.updateByPrimaryKey(role);

        //5.删除之前的角色-权限关联关系
        rolePermissionMapper.deleteByExample()
                .where(RolePermissionDynamicSqlSupport.roleId, isEqualTo(id))
                .build()
                .execute();

        //6.保存本次提交的权限列表
        List<Long> permsList = roleReqDto.getPermsList();
        for (int i = 0; i < permsList.size(); i++) {
            Long permId = permsList.get(i);
            Permission permission = permissionMapper.selectByPrimaryKey(permId);
            if(ObjUtil.isEmpty(permission)){
                throw new IllegalArgumentException("id为: "+ permId +" 的权限不存在");
            }
            List<Long> idList = new ArrayList<>();
            idList = permissionService.getParentPermIdList(permId,idList);

            //判断当前权限的父级权限是否存在(提交权限必须提交父级权限知道顶级权限)
            boolean subset = CollectionUtil.isSubset(permsList, idList);
            if(!subset){
                throw new IllegalArgumentException("id为: "+ permId +" 的权限缺少父级(或顶级)权限id");
            }

            RolePermission rolePermission = RolePermission.builder()
                    .roleId(id)
                    .permissionId(permId)
                    .build();
            rolePermissionMapper.insert(rolePermission);
        }


        //7.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","更新角色信息成功");
        return resultMap;
    }


    @Override
    public Map<String, Object> getRoleById(String userInfo,Long id){
        Role e = roleMapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("id为:"+id+"的角色信息不存在");
        }

        List<PermsVo> permsVoListTreeData = permissionService.getPermsVoListTreeDataByRoleId(id);
        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
        attr.put("name", e.getName());
        attr.put("orderNum", e.getOrderNum());
        attr.put("roleKey", e.getRoleKey());
        attr.put("useType", e.getUseType());
        attr.put("status", e.getStatus());
        attr.put("permsList", permsVoListTreeData);
        attr.put("createTime", e.getCreateTime());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getRoleListPageVo(String userInfo, RolePageReqDto rolePageReqDto){



        //2.查询语句构建
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Role>>>.QueryExpressionWhereBuilder builder = roleMapper.selectByExample().where();

        //3.根据查询条件封装查询命令
        //(1).关键字查询
        String name = rolePageReqDto.getName();
        if(!ObjUtil.isEmpty(name)){
            builder.and(RoleDynamicSqlSupport.name, isLike("%"+name+"%"));
        }

        //(2).角色标识
        String roleKey = rolePageReqDto.getRoleKey();
        if(!ObjUtil.isEmpty(roleKey)){
            builder.and(RoleDynamicSqlSupport.roleKey, isLike("%"+roleKey+"%"));
        }

        //(3).状态
        Byte status = rolePageReqDto.getStatus();
        if(!ObjUtil.isEmpty(status)){
            builder.and(RoleDynamicSqlSupport.status, isEqualTo(status));
        }

        //(4).时间范围
        Long startTime = rolePageReqDto.getStartTime();
        Long endTime = rolePageReqDto.getEndTime();
        if (startTime != null && endTime != null) {
            builder.and(RoleDynamicSqlSupport.createTime, isGreaterThanOrEqualTo(startTime));
            builder.and(RoleDynamicSqlSupport.createTime, isLessThanOrEqualTo(endTime));
        } else {
            if (startTime != null) {
                builder.and(RoleDynamicSqlSupport.createTime, isGreaterThanOrEqualTo(startTime));
            }
            if (endTime != null) {
                builder.and(RoleDynamicSqlSupport.createTime, isLessThanOrEqualTo(endTime));
            }
        }

        //4.排序
        builder.orderBy(RoleDynamicSqlSupport.orderNum.descending());

        //1.设置分页条件
        PageHelper.startPage(rolePageReqDto.getPageNum(), rolePageReqDto.getPageSize());

        //5.查询
        List<Role> list = builder.build().execute();

        //6.构建pageVo
        PageVo<Role> pageVo = new PageVo<>(list);

        //7.对象转为map分页输出
        return PageVo.of(
                pageVo,
                e -> {
                    Map<String, Object> attr = new HashMap<>();
                    attr.put("id", e.getId());
                    attr.put("name", e.getName());
                    attr.put("orderNum", e.getOrderNum());
                    attr.put("roleKey", e.getRoleKey());
                    attr.put("useType", e.getUseType());
                    attr.put("status", e.getStatus());
                    attr.put("createTime", e.getCreateTime());
                    return attr;
                }
        );
    }

    @Override
    public List<Role> getRoleList() {
        List<Role> roleList = roleMapper.selectByExample()
                .where()
                .build()
                .execute();
        return roleList;
    }
}