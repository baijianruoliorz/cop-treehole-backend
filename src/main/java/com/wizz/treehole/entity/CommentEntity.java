package com.wizz.treehole.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("tree_comment")
public class CommentEntity  implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long  messageId;
    private Long status;
    private String message;
    private String name;
    private Long userId;
    private Long likeNum;
    private Long reportNum;

}
