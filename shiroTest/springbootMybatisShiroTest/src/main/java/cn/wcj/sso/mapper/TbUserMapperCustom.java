package cn.wcj.sso.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import cn.wcj.sso.pojo.po.TbRole;

public interface TbUserMapperCustom {

	 List<String> findRoleNamesByUserName(@Param("userName") String userName)throws Exception  ;
	
	 List<String> findPermissionNamesByUserName(@Param("userName") String userName)throws Exception ;
	
}
