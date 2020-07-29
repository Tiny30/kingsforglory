package com.sqy.kingsforglory.glory.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sqy.kingsforglory.glory.json.SpDateDeSerializer;
import com.sqy.kingsforglory.glory.json.SpDateSerializer;

import java.util.Date;

public class Introduction {
    /*
    * 游戏推送实体类
    * */
    /**
     * 唯一标识
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id ;
    /*
    * 游戏推送的标题
    * */
    private String title;
    /*
    * 推送的内容
    *
    * */
    private  String content;
    /*
    * 图片
    * */
    private String img;
    /**
     * 创建时间
     */
    @JsonDeserialize(using = SpDateDeSerializer.class)
    @JsonSerialize(using = SpDateSerializer.class)
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
