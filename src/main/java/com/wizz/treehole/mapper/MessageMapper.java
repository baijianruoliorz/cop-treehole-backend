package com.wizz.treehole.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wizz.treehole.entity.MessageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Component
public interface MessageMapper  extends BaseMapper<MessageEntity> {
}
