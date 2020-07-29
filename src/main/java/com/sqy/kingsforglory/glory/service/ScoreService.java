package com.sqy.kingsforglory.glory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sqy.kingsforglory.glory.pojo.entity.Score;

import java.util.List;

public interface ScoreService extends IService<Score> {
     public List<Score> search(String key,String userId);
}
