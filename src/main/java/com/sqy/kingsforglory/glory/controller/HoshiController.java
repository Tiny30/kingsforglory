package com.sqy.kingsforglory.glory.controller;


import com.sqy.kingsforglory.glory.config.PageConfig;
import com.sqy.kingsforglory.glory.pojo.RespData;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Consumer;

public class HoshiController {

    @Autowired
    protected HttpServletRequest request ;

    protected <T> RespData<T> $(Consumer<RespData<T>> consumer){
        RespData<T> respData = RespData.failed(null) ;
        try {
            consumer.accept(respData);
        }catch (Exception e){
            e.printStackTrace();
            respData.success(false).setDesc(e.getMessage()); ;
        }
        return respData ;
    }

    protected PageConfig $page(){
        return PageConfig.get(request) ;
    }
}
