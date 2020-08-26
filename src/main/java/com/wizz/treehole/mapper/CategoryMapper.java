package com.wizz.treehole.mapper;

import com.wizz.treehole.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liqiqi&liqiqiorz
 * @since 2020-08-26
 */
@Mapper
@Repository
public interface CategoryMapper extends BaseMapper<Category> {

}
