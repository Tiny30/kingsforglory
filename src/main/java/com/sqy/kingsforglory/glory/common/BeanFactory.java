package com.sqy.kingsforglory.glory.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanFactory implements ApplicationContextAware {

    private static ApplicationContext applicationContext ;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanFactory.applicationContext = applicationContext ;
    }
    public static <T>T get(Class<T> cls){
        return applicationContext.getBean(cls) ;
    }
}