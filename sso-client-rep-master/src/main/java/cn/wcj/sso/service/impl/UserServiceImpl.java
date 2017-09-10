package cn.wcj.sso.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.wcj.sso.mapper.TbUserMapper;
import cn.wcj.sso.mapper.TbUserMapperCustom;
import cn.wcj.sso.pojo.po.TbUser;
import cn.wcj.sso.pojo.po.TbUserExample;
import cn.wcj.sso.pojo.po.TbUserExample.Criteria;

@Service
public class UserServiceImpl{

	@Autowired
	private TbUserMapper tbUserMapper   ;   //原生User的Mapper
	
	@Autowired
	private TbUserMapperCustom tbUserMapperCustom  ;   //自定义User的Mapper
	
	
	public TbUser findUserByUserName(String userName) throws Exception {
		TbUserExample example=new TbUserExample()   ;  //查询模板
		Criteria criteria = example.createCriteria();  //编写查询条件
		criteria.andUsernameEqualTo(userName)   ;  //根据用户名称查询
		List<TbUser> tbUsers=tbUserMapper.selectByExample(example);
		return tbUsers==null || tbUsers.size()==0 ? null : tbUsers.get(0) ;
	}

	public List<String> findRoleNamesByUserName(String userName)
			                                      throws Exception {
		
		List<String> roleNames = tbUserMapperCustom.findRoleNamesByUserName(userName);
		return roleNames  ;
	}

	public List<String> findPermissionNamesByUserName(String userName)
			                                            throws Exception {
		TbUser aTbUser = tbUserMapper.selectByPrimaryKey(1);
		List<String> permissionNames = tbUserMapperCustom.findPermissionNamesByUserName(userName);
		System.out.println("数据库查询的权限service-->permission:"+permissionNames);
		return permissionNames  ;
	}

	public List<TbUser> findAll() throws Exception {
		return tbUserMapper.selectByExample(null) ;
	}


}
