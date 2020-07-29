package com.sqy.kingsforglory.glory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sqy.kingsforglory.glory.dao.ScoreDao;
import com.sqy.kingsforglory.glory.pojo.entity.Arrangement;
import com.sqy.kingsforglory.glory.pojo.entity.Score;
import com.sqy.kingsforglory.glory.service.ScoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl extends HoshiService<ScoreDao, Score> implements ScoreService {
    @Override
    /**
     * 根据当前用户id查询战绩
     */
    public List<Score> search(String key,String userId) {

        List<Score> list;
        QueryWrapper<Score> queryWrapper = new QueryWrapper<Score>();
        queryWrapper.like("user_id", userId).and(wrapper->{
            wrapper.like("ID", key).or()
                    .like("grade", key);
        });
        if(needPage()){
            list = page(getPage(),queryWrapper).getRecords();
        }else {
            list = list(queryWrapper);
        }
        return list;
    }
}
