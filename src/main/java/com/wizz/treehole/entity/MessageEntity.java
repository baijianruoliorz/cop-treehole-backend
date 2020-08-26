package com.wizz.treehole.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("tree_message")
public class MessageEntity  implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String message;
    private Date createTime;
    //树洞状态 0不能被别人看见，1可以
    private Long  status;
    private Long userId;
    //点赞数量
    private Long likeNum;
    //举报数量
    private Long reportNum;
    //回复数量
    private Long reponseNum;
    //评论区状态，0不能被别人看见，1可以
    private Long commentStatus;

}
