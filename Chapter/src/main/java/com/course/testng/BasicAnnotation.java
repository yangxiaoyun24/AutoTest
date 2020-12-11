package com.course.testng;

import org.testng.annotations.*;

public class BasicAnnotation {


    //最基本的注解，用来把方法标记为测试的一部分
    @Test
    public void testCase1(){
        System.out.println("测试用例111");
    }

    @Test
    public void testCase2(){
        System.out.println("测试用例222");
    }


    //BeforeMethod、AfterMethod在每个测试case运行之前/之后，都会运行
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("BeforeMethod测试方法执行之前，运行的");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("AfterMethod测试方法执行之后，运行的");
    }

    //BeforeClass、AfterClass在每个测试类运行之前/后运行
    @BeforeClass
    public void beforeClass(){
        System.out.println("BeforeClass测试类执行之前，运行的");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("AfterClass测试类执行之后，运行的");
    }



    @BeforeSuite
    public void beforeSuite(){
        System.out.println("BeforeSuite测试套件执行之前，运行的");
    }

    @AfterSuite
    public void aterSuite(){
        System.out.println("AfterSuite测试套件执行之后，运行的");
    }

}
