package cn.wcj.sso.test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.wcj.sso.App;
/**
 * 
 * <p>Module:BaseJunitTest </p>
 * <p>Description: 单元测试的父类，方便所有程序进行单元测试</p>
 * <p>Company:Software College Of ZhengZhou University </p> 
 * @author SuccessKey(WangCJ)
 * @date 2017年8月4日 下午8:26:23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={App.class})
public class BaseJunitTest {}
