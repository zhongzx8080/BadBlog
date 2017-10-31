package com.xing.mapper;

import com.xing.entity.History;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xing on 2017/9/20.
 */
@Component
@Mapper
public interface HistoryMapper {


    /*
    *
    * 插入历史记录
    *
    * */
    @Insert("INSERT INTO `history`(`ip`,`location`,`uid`,`description`,`gmt_record`) VALUES (#{ip}, #{location},#{uid},#{description},NOW())")
    void insertHistory(History history);


    /*
    *
    * 选出所有记录
    * */
    @Select("SELECT `hid` ,`ip`,`location`,`uid`,`description`,`gmt_record` FROM `history` ")
    List<History> getAllHistory();

}
