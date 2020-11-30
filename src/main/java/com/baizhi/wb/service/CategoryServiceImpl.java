package com.baizhi.wb.service;

import com.baizhi.wb.annotcation.AddLog;
import com.baizhi.wb.annotcation.AddRedis;
import com.baizhi.wb.annotcation.DelRedis;
import com.baizhi.wb.dao.CategoryMapper;
import com.baizhi.wb.entity.Category;
import com.baizhi.wb.entity.CategoryExample;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;
    @Transactional(propagation = Propagation.SUPPORTS)
    @AddRedis
    @Override
    public HashMap<String,Object> findAllOne(Integer pageNumber, Integer pageShow) {
        HashMap<String,Object> map=new HashMap<>();
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andLevelsEqualTo(1);
        RowBounds rowBounds = new RowBounds((pageNumber - 1) * pageShow, pageShow);
        List<Category> categories = categoryMapper.selectByExampleAndRowBounds(categoryExample,rowBounds);
        int count = categoryMapper.selectCountByExample(categoryExample);
        int total=count%pageShow==0?count/pageShow:count/pageShow+1;
        map.put("page",pageNumber);
        map.put("total",total);
        map.put("records",count);
        map.put("rows",categories);
        return map;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @AddRedis
    @Override
    public HashMap<String,Object> findAllTwoByOne(Integer pageNumber, Integer pageShow,String id) {
        HashMap<String, Object> map = new HashMap<>();
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andParentIdIsNotNull().andParentIdEqualTo(id);
        RowBounds rowBounds = new RowBounds((pageNumber - 1) * pageShow, pageShow);
        int count = categoryMapper.selectCountByExample(categoryExample);
        int total = count%pageShow==0?count/pageShow:count/pageShow+1;
        List<Category> categories = categoryMapper.selectByExampleAndRowBounds(categoryExample, rowBounds);
        map.put("page",pageNumber);
        map.put("total",total);
        map.put("records",count);
        map.put("rows",categories);
        return map;
    }
    @AddLog("添加类别")
    @DelRedis
    @Override
    public void insert(Category category) {
        category.setId(UUID.randomUUID().toString());
        if (category.getParentId()==null){
            category.setLevels(1);
        }else {
            category.setLevels(2);
        }
        categoryMapper.insert(category);
    }
    @AddLog("删除类别")
    @DelRedis
    @Override
    public HashMap<String,Object> delete(Category category) {
        Category category1 = categoryMapper.selectByPrimaryKey(category);
        HashMap<String,Object> map=new HashMap<>();
        if (category1.getLevels()==1){
           CategoryExample categoryExample = new CategoryExample();
           categoryExample.createCriteria().andParentIdEqualTo(category.getId());
           int count = categoryMapper.selectCountByExample(categoryExample);
           if (count==0){
               categoryMapper.delete(category);
           }else {
               map.put("no","该类别下存在二级类别");
           }
       }else {
           categoryMapper.delete(category);
       }
        return map;
    }
    @AddLog("修改类别")
    @DelRedis
    @Override
    public void update(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
    }
    @AddRedis
    @Override
    public List<Category> findAllOne() {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andLevelsEqualTo(1);
        List<Category> categories = categoryMapper.selectByExample(categoryExample);
        return categories;
    }
}
