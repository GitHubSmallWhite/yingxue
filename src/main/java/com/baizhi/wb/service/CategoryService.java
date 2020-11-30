package com.baizhi.wb.service;

import com.baizhi.wb.entity.Category;

import java.util.HashMap;
import java.util.List;

public interface CategoryService {
    public HashMap<String,Object> findAllOne(Integer pageNumber, Integer pageShow);
    public HashMap<String,Object> findAllTwoByOne(Integer pageNumber, Integer pageShow,String id);
    public void insert(Category category);
    public HashMap<String,Object> delete(Category category);
    public void update(Category category);
    public List<Category> findAllOne();
}
