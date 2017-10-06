package com.xing.mapper;

import com.xing.entity.History;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

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
    @Insert("INSERT INTO `history`(`ip`,`uid`,`description`,`gmt_record`) VALUES (#{ip},#{uid},#{description},NOW())")
    void insertHistory(History history);


}
