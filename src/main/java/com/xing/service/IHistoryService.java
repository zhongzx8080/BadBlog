package com.xing.service;

import com.xing.entity.History;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xing on 2017/9/20.
 */
@Service
public interface IHistoryService {
    void insertHistory(History history);

    HashMap<String,String> getAllHistory();
}
