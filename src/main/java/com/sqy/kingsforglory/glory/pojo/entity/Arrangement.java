package com.sqy.kingsforglory.glory.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sqy.kingsforglory.glory.json.SpDateDeSerializer;
import com.sqy.kingsforglory.glory.json.SpDateSerializer;

import java.util.Date;

public class Arrangement {
    /**
     * 安排比赛和赛事回放
     */
    /**
     * 唯一标识
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id ;
    /*
    * 比赛名称
    * */
    private String name;
    /*
    * 比赛队伍
    * */
    private String team;
    /**
     * 比赛场数
     */private Integer times;

    /**
     * 比赛日期
     */
    @JsonDeserialize(using = SpDateDeSerializer.class)
    @JsonSerialize(using = SpDateSerializer.class)
    private Date gameTime;
    /**
     * 视频
     */
    private String video;

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Date getGameTime() {
        return gameTime;
    }

    public void setGameTime(Date gameTime) {
        this.gameTime = gameTime;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
