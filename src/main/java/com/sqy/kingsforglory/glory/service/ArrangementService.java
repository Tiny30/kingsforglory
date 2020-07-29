package com.sqy.kingsforglory.glory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sqy.kingsforglory.glory.pojo.entity.Arrangement;


import java.util.List;

public interface ArrangementService extends IService<Arrangement> {
    List<Arrangement> bySearch(String key);
}
