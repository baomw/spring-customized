package com.baomw.aspect;

import com.baomw.annotation.AfterBaomw;
import com.baomw.annotation.AopJ;
import com.baomw.annotation.AroundBaomw;
import com.baomw.annotation.BeforeBaomw;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * @author baomw
 * @create 2018-11-19 下午 1:05
 */
@AopJ
@Component
public class TestAop {

    @BeforeBaomw("com.baomw.dao")
    public void testBefore(){
        System.out.println("before   ------------------  测试成功！");
    }

    @AfterBaomw("com.baomw.dao")
    public void testAfter(){
        System.out.println("after   ------------------  测试成功！");
    }

    @AroundBaomw("com.baomw.dao")
    public void testAround(){
        System.out.println("around   ------------------  测试成功！");
    }
}
