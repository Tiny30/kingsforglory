package com.sqy.kingsforglory.glory.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sqy.kingsforglory.glory.json.SpDateDeSerializer;
import com.sqy.kingsforglory.glory.json.SpDateSerializer;

import java.util.Date;

public class Score {
    public interface Level{
        String BRONZE = "bronze" ;//青铜
        String SILVER = "silver" ;//白银
        String GOLD = "gold";//黄金
        String  DIAMONDS = "diamonds";// 钻石
        String KING = "king";// 王者
    }
    /**
     * 唯一标识
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id ;
    /**
     * 战绩
     */
    private String grade;
    /**
     * 使用英雄
     */
    private String hero;
    /**
     * 获取用户类
     */
    private String userId ;
    @TableField(exist = false)
    private User user;
    /**
     * 获取有比赛安排类
     */
    private Arrangement arrange;
    /**
     * 时间
     */
    @JsonDeserialize(using = SpDateDeSerializer.class)
    @JsonSerialize(using = SpDateSerializer.class)
    private Date time;

    /**
     * 段位,初始为青铜
     * @return
     */
    private String level = Level.BRONZE;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getHero() {
        return hero;
    }

    public void setHero(String hero) {
        this.hero = hero;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Arrangement getArrange() {
        return arrange;
    }

    public void setArrange(Arrangement arrange) {
        this.arrange = arrange;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
