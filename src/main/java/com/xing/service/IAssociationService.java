package com.xing.service;


import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xing on 2017/9/6.
 */
@Service
public interface IAssociationService {
    void saveAssociation(Integer aid,List<Integer> cidList);

    List<Integer> listCidByAid(Integer aid);

    List<Integer> listAidByCid(Integer cid);

    void deleteCidByAid(Integer aid);
}
