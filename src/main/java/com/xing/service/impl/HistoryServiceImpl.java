package com.xing.service.impl;

import com.xing.entity.History;
import com.xing.mapper.HistoryMapper;
import com.xing.service.IHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

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

    @Override
    public HashMap<String, String> getAllHistory() {
        List<History> histories = historyMapper.getAllHistory();
        HashMap<String, String> historyHashMap = new HashMap<>();
        histories.forEach(history -> {
            historyHashMap.put(history.getIp(), history.getLocation());
        });
        return historyHashMap;
    }
}
