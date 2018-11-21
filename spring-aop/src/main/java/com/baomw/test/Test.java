package com.baomw.test;

import com.baomw.config.Appconfig;
import com.baomw.dao.IndexDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * √Ë ˆ:
 *
 * @author baomw
 * @create 2018-11-19 …œŒÁ 10:16
 */
public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext
                    = new AnnotationConfigApplicationContext(Appconfig.class);
        IndexDao indexDao = annotationConfigApplicationContext.getBean(IndexDao.class);
        indexDao.query();
    }
}
