package com.xing.mapper;

import com.xing.entity.Category;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xing on 2017/9/5.
 */
@Component
@Mapper
public interface CategoryMapper {


    /*
    *
    * 新增分类
    *
    * */

    @Insert("INSERT INTO `category`(`uid`,`category`,`gmt_create`) VALUES(#{uid},#{category},NOW()) ")
    void insertCategory(@Param("uid") Integer uid, @Param("category") String category);


    /*
    *
    * 通过用户id 获取所有分类
    * */
    @Select("SELECT `cid`,`uid`,`category`,`gmt_create` FROM `category` WHERE `uid` = #{uid}")
    @Results(value = {
            @Result(column = "gmt_create", property = "gmtCreate", javaType = java.util.Date.class, jdbcType = JdbcType.TIMESTAMP)
    })
    List<Category> listCategoryByUid(@Param("uid") Integer uid);




    /*
    *
    * 通过 cid 获取分类对象
    *
    * */

    @Select("SELECT `cid`,`uid`,`category`,`gmt_create` FROM `category` WHERE `cid` = #{cid} ")
    @Results(value = {
            @Result(column = "gmt_create", property = "gmtCreate", javaType = java.util.Date.class, jdbcType = JdbcType.TIMESTAMP)
    })
    Category getCategoryByCid(@Param("cid") Integer cid);


    /*
    *
    *
    * 通过分类id获取分类 对象list(新建文章时用到)
    * */
    @Select("<script>SELECT `cid`,`uid`,`category`,`gmt_create` FROM `category` WHERE  `cid` IN <foreach collection=\"cidList\" item=\"c\" open=\"(\" separator=\",\" close=\")\" >#{c}</foreach></script>")
    @Results(value = {
            @Result(column = "gmt_create", property = "gmtCreate", javaType = java.util.Date.class, jdbcType = JdbcType.TIMESTAMP)
    })
    List<Category> listCategoryByCid(@Param("cidList") List<Integer> cidList);


    /*
    *
    *更新分类名称
    *
    * */

    @Update("UPDATE `category` SET `category` = #{category} WHERE `cid` = #{cid} AND `uid` = #{uid} ")
    void updateCategory(@Param("cid") Integer cid, @Param("uid") Integer uid, @Param("category") String category);


    /*
    *
    * 判断分类名称是否存在
    * */
    @Select("SELECT `cid`,`uid`,`category`,`gmt_create` FROM `category` WHERE `uid` = #{uid} AND `category` = #{category} ")
    @Results(value = {
            @Result(column = "gmt_create", property = "gmtCreate", javaType = java.util.Date.class, jdbcType = JdbcType.TIMESTAMP)
    })
    List<Category> existCategory(@Param("uid") Integer uid, @Param("category") String category);


    /*
    *
    * 删除分类
    * */

    @Delete("<script>DELETE FROM `category` WHERE `uid` = #{uid} AND `cid` IN <foreach collection=\"cidList\" item=\"c\" open=\"(\" separator=\",\" close=\")\" >#{c}</foreach></script>  ")
    void deleteCategories(@Param("uid") Integer uid, @Param("cidList") List<Integer> cidList);
}
