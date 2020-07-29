package com.sqy.kingsforglory.glory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sqy.kingsforglory.glory.dao.IntroductionDao;
import com.sqy.kingsforglory.glory.pojo.entity.Introduction;
import com.sqy.kingsforglory.glory.service.IntroductionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntroductionServiceImpl extends HoshiService<IntroductionDao, Introduction> implements IntroductionService {

    @Override
    public List<Introduction> bySearch(String key) {
        key = "%" + key + "%";
        List<Introduction> list;
        QueryWrapper<Introduction> queryWrapper = new QueryWrapper<Introduction>();
        queryWrapper.like("title",key);
        if(needPage()){
            list = page(getPage(),queryWrapper).getRecords();
        }else {
            list = list(queryWrapper);
        }
        return list;
    }
}
