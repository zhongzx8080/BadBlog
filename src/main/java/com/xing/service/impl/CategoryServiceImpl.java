package com.xing.service.impl;

import com.xing.entity.Category;
import com.xing.mapper.CategoryMapper;
import com.xing.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xing on 2017/9/5.
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public void insertCategory(Integer uid, String category) {
        categoryMapper.insertCategory(uid,category);
    }

    @Override
    public List<Category> listCategoryByUid(Integer uid) {
        return categoryMapper.listCategoryByUid(uid);
    }

    @Override
    public List<Category> listCategoryByCid(List<Integer> cidList) {
        return categoryMapper.listCategoryByCid(cidList);
    }

    @Override
    public Category getCategoryByCid(Integer cid) {
        return categoryMapper.getCategoryByCid(cid);
    }

    @Override
    public void updateCategory(Integer cid, Integer uid, String category) {
        categoryMapper.updateCategory(cid,uid,category);
    }

    @Override
    public List<Category> existCategory(Integer uid, String category) {
        return categoryMapper.existCategory(uid,category);
    }

    @Override
    public void deleteCategories(Integer uid, List<Integer> cidList) {
        categoryMapper.deleteCategories(uid,cidList);
    }
}
