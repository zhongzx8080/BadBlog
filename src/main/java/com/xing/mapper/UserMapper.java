package com.xing.mapper;

import com.xing.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

/**
 * Created by xing on 2017/9/3.
 */
@Component
@Mapper
public interface UserMapper {

    /*
    *
    * 通过用户ID获取 User
    *
    * */
    @Select("SELECT `uid`,`username`,`password`,`email`,`gmt_register`,`level` FROM `user` WHERE `uid` = #{uid}")
    @Results(value = {
            @Result(column = "gmt_register", property = "gmtRegister", javaType = java.util.Date.class, jdbcType = JdbcType.TIMESTAMP)
    })
    User getUserById(@Param("uid") Integer uid);


    /*
    *
    * 通过用户名获取 User
    * */
    @Select("SELECT `uid`,`username`,`password`,`email`,`gmt_register`,`level` FROM `user` WHERE `username` = #{username}")
    @Results(value = {
            @Result(column = "gmt_register", property = "gmtRegister", javaType = java.util.Date.class, jdbcType = JdbcType.TIMESTAMP)
    })
    User getUserByUsername(@Param("username") String username);



    /*
    *
    * 通过 email 获取用户
    *
    * */

    @Select("SELECT `email` FROM `user` WHERE `email` = #{email}")
    String getUserByEmail(@Param("email") String email);



    /*
    *
    * (忘记密码) 通过邮箱更新密码
    *
    * */

    @Update("UPDATE `user` SET `password` = #{password} WHERE `email` = #{email} ")
    void updatePasswordByEmail(@Param("email") String email, @Param("password") String password);


    /*
    *
    * 添加新用户并返回用户 ID
    *
    * */

    @Insert("INSERT INTO `user` (`username`,`password`,`email`,`gmt_register`) VALUES (#{username},#{password},#{email},NOW())  ")
    @Options(useGeneratedKeys = true, keyColumn = "uid", keyProperty = "uid")
    int saveUser(@Param("username") String username, @Param("password") String password, @Param("email") String email);
}
