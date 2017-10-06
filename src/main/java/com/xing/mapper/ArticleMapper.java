package com.xing.mapper;

import com.xing.entity.Article;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xing on 2017/9/6.
 */
@Component
@Mapper
public interface ArticleMapper {

    /*
    *ID
    * 插入文章并返回插入文章
    * */

    @Insert("INSERT INTO `article` (`uid`,`title`,`markdown`,`html`,`gmt_post`) VALUES (#{uid},#{title},#{markdown},#{html},NOW()) ")
    @SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "aid", keyColumn = "aid", before = false, resultType = int.class)
    @Results(value = {
            @Result(column = "gmt_post", property = "gmtPost", javaType = java.util.Date.class, jdbcType = JdbcType.TIMESTAMP)
    })
    int insertArticle(Article article);





    /*
    *
    * 通过 aid 获取文章对象
    * */

    @Select("SELECT `aid`,`uid`,`title`,`markdown`,`html`,`gmt_post`,`view` FROM `article` WHERE `aid` = #{aid} ")
    @Results(value = {@Result(id = true, property = "aid", column = "aid"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "title", column = "title"),
            @Result(property = "markdown", column = "markdown"),
            @Result(property = "html", column = "html"),
            @Result(property = "gmtPost", column = "gmt_post", javaType = java.util.Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "view", column = "view")
    }
    )
    Article getArticleByAid(@Param("aid") Integer aid);






    /*
    *
    * 通过 aid 获取文章 articleList
    * */

    @Select("<script>SELECT `aid`,`uid`,`title`,`markdown`,`html`,`gmt_post`,`view` FROM `article` WHERE  `aid` in <foreach collection=\"aidList\" item=\"c\" open=\"(\" separator=\",\" close=\")\" >#{c}</foreach> ORDER BY gmt_post DESC </script>")
    @Results(value = {
            @Result(column = "gmt_post", property = "gmtPost", javaType = java.util.Date.class, jdbcType = JdbcType.TIMESTAMP)
    })
    List<Article> listArticleByAidList(@Param("aidList") List<Integer> aidList);







    /*
    *
    * 通过 uid 获取 articleList，并按发表时间降序排列
    *
    * */

    @Select("SELECT `aid`,`uid`,`title`,`markdown`,`html`,`gmt_post`,`view` FROM `article` WHERE  `uid` = #{uid} ORDER BY `gmt_post` DESC ")
    @Results(value = {@Result(id = true, property = "aid", column = "aid"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "title", column = "title"),
            @Result(property = "markdown", column = "markdown"),
            @Result(property = "html", column = "html"),
            @Result(property = "gmtPost", column = "gmt_post", javaType = java.util.Date.class, jdbcType = JdbcType.TIMESTAMP),
            @Result(property = "view", column = "view")
    }
    )
    List<Article> listArticleByUid(@Param("uid") Integer uid);






    /*
    *
    * 分页查询
    * */

    @Select("SELECT `aid`,`uid`,`title`,`markdown`,`html`,`gmt_post`,`view` FROM `article` WHERE  `uid` = #{uid} LIMIT #{startIndex},#{count} ")
    @Results(value = {
            @Result(column = "gmt_post", property = "gmtPost", javaType = java.util.Date.class, jdbcType = JdbcType.TIMESTAMP)
    })
    List<Article> listArticleByPage(@Param("startIndex") Integer startIndex, @Param("count") Integer count, @Param("uid") Integer uid);





    /*
    *
    *
    * 设置浏览数 + 1
    * */

    @Update("UPDATE `article` SET `view` = `view` + #{times} WHERE `aid` = #{aid} ")
    void updateView(@Param("aid") Integer aid, @Param("times") Integer times);








    /*
    *
    *选出浏览量最多的 X 篇文章
    *
    * */

    @Select("SELECT `aid`,`uid`,`title`,`markdown`,`html`,`gmt_post`,`view` FROM `article` WHERE  `uid` = #{uid} ORDER BY `view` DESC LIMIT #{count}")
    @Results(value = {
            @Result(column = "gmt_post", property = "gmtPost", javaType = java.util.Date.class, jdbcType = JdbcType.TIMESTAMP)
    })
    List<Article> viewMost(@Param("uid") Integer uid, @Param("count") Integer count);







    /*
    *
    * 编辑文章
    * */

    @Update("UPDATE `article` SET `title` = #{title},`markdown` = #{markdown},`html` = #{html} WHERE `aid` = #{aid} ")
    void updateArticle(@Param("aid") Integer aid, @Param("title") String title, @Param("markdown") String markdown, @Param("html") String html);






    /*
    *
    * 删除文章
    *
    * */

    @Delete("<script>DELETE FROM `article` WHERE `uid` = #{uid} AND `aid` IN <foreach collection=\"aidList\" item=\"aid\" open=\"(\" separator=\",\" close=\")\"  > #{aid}  </foreach> </script>")
    void deleteArticle(@Param("aidList") List<Integer> aidList, @Param("uid") Integer uid);


    /*
    *
    *  搜索
    * */

    //@Select("SELECT `aid`,`uid`,`title`,`markdown`,`html`,`gmt_post`,`view` FROM `article` WHERE  `uid` = #{uid} AND `title` LIKE '%${keyword}%' ")
    @Select("SELECT DISTINCT `aid`,`uid`,`title`,`markdown`,`html`,`gmt_post`,`view` FROM `article` WHERE  `uid` = #{uid} AND ( `title` LIKE '%${keyword}%' OR `markdown` LIKE '%${keyword}%' ) ")
    @Results(value = {
            @Result(column = "gmt_post", property = "gmtPost", javaType = java.util.Date.class, jdbcType = JdbcType.TIMESTAMP)
    })
    List<Article> search(@Param("keyword") String keyword, @Param("uid") Integer uid);
}
