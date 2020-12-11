package com.course.testng;

import org.testng.annotations.Test;

public class TimeOutTest {

    @Test(timeOut = 3000)//单位:毫秒
    public void testSuccess() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Test(timeOut = 3000)
    public void testFailed() throws InterruptedException {
        Thread.sleep(4000);
    }
}
