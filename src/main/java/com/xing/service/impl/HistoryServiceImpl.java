package com.xing.service.impl;

import com.xing.entity.History;
import com.xing.mapper.HistoryMapper;
import com.xing.service.IHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xing on 2017/9/20.
 */
@Service
public class HistoryServiceImpl implements IHistoryService {

    @Autowired
    private HistoryMapper historyMapper;

    @Override
    public void insertHistory(History history) {
        historyMapper.insertHistory(history);
    }
}
