package com.baomw.test;

import com.baomw.config.Appconfig;
import com.baomw.dao.IndexDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * √Ë ˆ:
 * ≤‚ ‘¿‡
 *
 * @author baomw
 * @create 2018-11-18 œ¬ŒÁ 11:37
 */
public class Text {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(Appconfig.class);
        annotationConfigApplicationContext.getBean(IndexDao.class).query2();
        annotationConfigApplicationContext.getBean(IndexDao.class).query();
    }

}
