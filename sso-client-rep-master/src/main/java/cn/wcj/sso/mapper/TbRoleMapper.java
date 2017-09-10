package cn.wcj.sso.mapper;

import cn.wcj.sso.pojo.po.TbRole;
import cn.wcj.sso.pojo.po.TbRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbRoleMapper {
    int countByExample(TbRoleExample example);

    int deleteByExample(TbRoleExample example);

    int insert(TbRole record);

    int insertSelective(TbRole record);

    List<TbRole> selectByExample(TbRoleExample example);

    int updateByExampleSelective(@Param("record") TbRole record, @Param("example") TbRoleExample example);

    int updateByExample(@Param("record") TbRole record, @Param("example") TbRoleExample example);
}