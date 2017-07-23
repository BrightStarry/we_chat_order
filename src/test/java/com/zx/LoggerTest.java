package com.zx;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 97038 on 2017-07-22.
 * 日志测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoggerTest {
    //日志
    private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);


    @Test
    public void test1(){
        logger.debug("a");
        logger.info("b");
        logger.error("c");
        logger.info("a:{},b{},c = {}",1,3,"323");
    }

}
