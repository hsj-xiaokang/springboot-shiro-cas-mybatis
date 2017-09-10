package cn.wcj.sso.mapper;

import cn.wcj.sso.pojo.po.TbUserRole;
import cn.wcj.sso.pojo.po.TbUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbUserRoleMapper {
    int countByExample(TbUserRoleExample example);

    int deleteByExample(TbUserRoleExample example);

    int insert(TbUserRole record);

    int insertSelective(TbUserRole record);

    List<TbUserRole> selectByExample(TbUserRoleExample example);

    int updateByExampleSelective(@Param("record") TbUserRole record, @Param("example") TbUserRoleExample example);

    int updateByExample(@Param("record") TbUserRole record, @Param("example") TbUserRoleExample example);
}