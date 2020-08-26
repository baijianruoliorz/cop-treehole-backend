package com.wizz.treehole.ktController

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.wizz.treehole.commonUtils.R
import com.wizz.treehole.entity.MessageEntity
import com.wizz.treehole.mapper.MessageMapper
import com.wizz.treehole.utils.Query
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/message")
 open class MessageController {

    @Autowired
    lateinit var messgaeMapper:MessageMapper;

    @RequestMapping("/list")
    fun list(params:Map<String,Objects>):R
    {

        val selectPage = messgaeMapper.selectPage(Query<MessageEntity>().getPage(params),null);
        return R.ok().data("data",selectPage);

    }
}