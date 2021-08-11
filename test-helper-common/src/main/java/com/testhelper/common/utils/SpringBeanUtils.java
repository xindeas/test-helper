package com.testhelper.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 静态方法获取bean工具
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
@Component
public class SpringBeanUtils implements ApplicationContextAware {
    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanUtils.context = applicationContext;
    }

    /**
     * 通过class获取bean
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return (T) context.getBean(clazz);
    }

    /**
     * 通过名称获取bean
     * @param name
     * @return
     * @throws BeansException
     */
    public static Object  getBean(String name) throws BeansException {
        return context.getBean(name);
    }
}
