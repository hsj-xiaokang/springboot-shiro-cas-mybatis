package cn.wcj.sso.service;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import cn.wcj.sso.pojo.po.TbUser;


/**
 * 
 * <p>Module:IUserService </p>
 * <p>Description:用户服务接口 </p>
 * <p>Company:Software College Of ZhengZhou University </p> 
 * @author SuccessKey(WangCJ)
 * @date 2017年8月4日 下午8:38:54
 */
public interface IUserService {

	TbUser findUserByUserName(String userName)throws Exception   ;
	
	List<String> findRoleNamesByUserName(String userName)throws Exception  ;
	
	List<String> findPermissionNamesByUserName(String userName)throws Exception ;
	
	List<TbUser> findAll()throws Exception   ;
	
}
