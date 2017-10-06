package com.xing.service;

import com.xing.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xing on 2017/9/5.
 */
@Service
public interface ICategoryService {

    void insertCategory(Integer uid, String category);

    List<Category> listCategoryByUid(Integer uid);

    List<Category> listCategoryByCid(List<Integer> cidList);

    Category getCategoryByCid(Integer cid);

    void updateCategory(Integer cid, Integer uid, String category);

    List<Category> existCategory(Integer uid, String category);

    void deleteCategories(Integer uid, List<Integer> cidList);
}
