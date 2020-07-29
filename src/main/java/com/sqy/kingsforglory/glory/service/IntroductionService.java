package com.sqy.kingsforglory.glory.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sqy.kingsforglory.glory.pojo.entity.Introduction;

import java.util.List;

public interface IntroductionService extends IService<Introduction> {

    List<Introduction> bySearch(String key) ;


}
