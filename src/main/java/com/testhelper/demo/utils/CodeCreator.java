package com.testhelper.demo.utils;

import com.testhelper.demo.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Xindeas
 * @Date: 2020/12/17 14:23
 */
public class CodeCreator {
    private String entityName = "";
    private String poName = "";
    public static void entityCreator(String tableName) {
        BaseServiceImpl baseService = (BaseServiceImpl)SpringBeanUtils.getBean("BaseService");
        baseService.entityCreator(tableName);
    }
}
