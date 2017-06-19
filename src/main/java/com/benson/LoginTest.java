package com.benson;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/19 0019.
 */
public class LoginTest {

    @Test(dataProvider = "login")
    public void test(Map<String, String> map) {
        System.out.println(map.get("userName"));
        System.out.println(map.get("password"));
    }

    @DataProvider(name = "login")
    public Object[][] getData(Method method) throws IOException {
        return ExcelUtils.getData(this, method);
    }
}
