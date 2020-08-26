package com.wizz.treehole.service.impl;

import com.wizz.treehole.entity.Category;
import com.wizz.treehole.mapper.CategoryMapper;
import com.wizz.treehole.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liqiqi&liqiqiorz
 * @since 2020-08-26
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
