package com.wizz.treehole.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wizz.treehole.commonUtils.R;
import com.wizz.treehole.entity.MessageEntity;
import com.wizz.treehole.mapper.MessageMapper;
import com.wizz.treehole.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liqiqi&liqiqiorz
 * @since 2020-08-26
 */
@RestController
@RequestMapping("/treehole/tuser")
public class TUserController {

    @Autowired
   private MessageMapper messageMapper;
    @RequestMapping("/list")
    public R list(Map<String,Object> params)
    {
        IPage<MessageEntity> messageEntityIPage = messageMapper.selectPage(new Query<MessageEntity>().getPage(params), null);
        return R.ok().data("data",messageEntityIPage);
    }
}

