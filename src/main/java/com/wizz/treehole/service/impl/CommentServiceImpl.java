package com.wizz.treehole.service.impl;

import com.wizz.treehole.entity.Comment;
import com.wizz.treehole.mapper.CommentMapper;
import com.wizz.treehole.service.CommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper,Comment> implements CommentService {

}
