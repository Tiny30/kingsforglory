package com.sqy.kingsforglory.glory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sqy.kingsforglory.glory.dao.ArrangementDao;
import com.sqy.kingsforglory.glory.dao.IntroductionDao;
import com.sqy.kingsforglory.glory.pojo.entity.Arrangement;
import com.sqy.kingsforglory.glory.pojo.entity.Introduction;
import com.sqy.kingsforglory.glory.service.ArrangementService;
import com.sqy.kingsforglory.glory.service.IntroductionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArrangementServiceImpl extends HoshiService<ArrangementDao, Arrangement> implements ArrangementService {

    /**
     * 根据时间查询该时间段的视频，即视频回放
     * @param key
     * @return
     */
    @Override
    public List<Arrangement> bySearch(String key) {
        key = "%" + key + "%";
        List<Arrangement> list;
        QueryWrapper<Arrangement> queryWrapper = new QueryWrapper<Arrangement>();
        queryWrapper.select("video").like("gameTime",key);
        if(needPage()){
            list = page(getPage(),queryWrapper).getRecords();
        }else {
            list = list(queryWrapper);
        }
        return list;
    }
}
