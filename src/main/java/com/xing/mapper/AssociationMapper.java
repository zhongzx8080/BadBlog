package com.xing.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xing on 2017/9/6.
 */
@Component
@Mapper
public interface AssociationMapper {
    /*
    *
    * 关联aid与cidList
    * */
    @Insert("<script> INSERT IGNORE INTO `association` (`aid`,`cid`) VALUES <foreach collection=\"cidList\" item=\"cid\" open=\"(\" separator=\"),(\" close=\")\">#{aid},#{cid}</foreach> </script>")
    void saveAssociation(@Param("aid") Integer aid, @Param("cidList") List<Integer> cidList);





    /*
    *
    *通过 aid 查询其所属分类
    *
    * */

    @Select("SELECT `cid` FROM `association` WHERE `aid` = #{aid} ")
    List<Integer> listCidByAid(@Param("aid") Integer aid);







    /*
    *
    * 通过 cid 查询文章 aid
    * */
    @Select("SELECT `aid` FROM `association` WHERE `cid` = #{cid} ")
    List<Integer> listAidByCid(@Param("cid") Integer cid);








    /*
    *
    * 通过 aid 删除其所属分类
    * */

    @Delete("DELETE FROM `association` WHERE `aid` = #{aid} ")
    void deleteCidByAid(@Param("aid") Integer aid);

}
